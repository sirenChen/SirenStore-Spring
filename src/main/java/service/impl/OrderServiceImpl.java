package service.impl;

import dao.OrderDao;
import entity.PageBean;
import entity.User;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.order.Order;
import entity.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;
import utils.DataSourceUntil;
import utils.UUIDUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Siren Chen.
 */
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("orderDaoSpringJdbc")
    private OrderDao orderDao;

    @Override
    public void placeOrder(String oid, String receiverName, String receiverAdd, String receiverPhone) throws SQLException {
        Order order = orderDao.findOrderById(oid);
        order.setState(1);
        order.setReceiverName(receiverName);
        order.setReceiverAdd(receiverAdd);
        order.setReceiverPhone(receiverPhone);

        orderDao.updateOrder(order);
    }

    @Override
    public Order genOrderFromCart(Cart cart, User user) throws SQLException {
        // set order
        Order order = new Order();
        order.setUser(user);
        order.setOid(UUIDUtil.genId());
        order.setOrderTime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(0);

        // set order item
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOoid(UUIDUtil.genId());
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());

            order.getOrderItems().add(orderItem);
        }

        insert2Database(order);
        return order;
    }

    @Override
    public PageBean<Order> showOrderList(int curPage, User user) throws SQLException, InvocationTargetException, IllegalAccessException {
        // set the orders list
        List<Order> orders = orderDao.findOrdersByUser(curPage, 4, user);
        for (Order order: orders) {
            for (OrderItem orderItem : orderDao.findOrderItemByOrder(order)) {
                order.getOrderItems().add(orderItem);
            }
        }

        // set pagination info
        PageBean<Order> page = new PageBean<>();
        page.setPageSize(4);
        page.setTotalSize(orderDao.countOrderByUser(user));
        page.setList(orders);

        return page;
    }

    @Override
    public Order showOrderInfo(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        Order order = orderDao.findOrderById(oid);

        // set order items
        List<OrderItem> orderItems = orderDao.findOrderItemByOrder(order);
        for (OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
        }

        return order;
    }


    // because of two table involved in this case, transaction management needed
    @Transactional
    public void insert2Database(Order order) throws SQLException {

        orderDao.insertOrder(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderDao.insertOrderItem(orderItem);
        }

    }
}
