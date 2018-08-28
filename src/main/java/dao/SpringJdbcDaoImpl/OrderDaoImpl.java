package dao.SpringJdbcDaoImpl;

import dao.OrderDao;
import dao.SpringJdbcDaoImpl.daoMapper.OrderItemMapper;
import dao.SpringJdbcDaoImpl.daoMapper.OrderMapper;
import entity.User;
import entity.order.Order;
import entity.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@Component("orderDaoSpringJdbc")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (oid, total, orderTime, state, receiverAdd, receiverName, receiverPhone, uid) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, order.getOid(), order.getTotal(), order.getOrderTime(),
                order.getState(), order.getReceiverAdd(), order.getReceiverName(), order.getReceiverPhone(), order.getUser().getUid());
    }

    @Override
    public void insertOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO orderItem (ooid, quantity, subtotal, pid, oid) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(sql, orderItem.getOoid(), orderItem.getQuantity(),
                orderItem.getSubtotal(),orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
    }

    @Override
    public Order findOrderById(String oid) throws SQLException {
        String sql = "SELECT * FROM orders WHERE oid=?";
        try {
            return jdbcTemplate.queryForObject(sql, new OrderMapper(), oid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Order> findOrdersByUser(int curPage, int pageSize, User user) throws SQLException {
        String sql = "SELECT * FROM orders WHERE uid=? ORDER BY orderTime DESC limit ?,?";

        return jdbcTemplate.query(sql, new OrderMapper(), user.getUid(), (curPage-1)*pageSize, pageSize);
    }

    @Override
    public List<OrderItem> findOrderItemByOrder(Order order) throws SQLException, InvocationTargetException, IllegalAccessException {
        String sql = "SELECT * FROM orderItem oi, product p WHERE oi.pid = p.pid AND oi.oid=?";

        return jdbcTemplate.query(sql, new OrderItemMapper(order), order.getOid());
    }

    @Override
    public long countOrderByUser(User user) throws SQLException {
        String sql = "SELECT COUNT(*) FROM orders WHERE uid=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{user.getUid()}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET state=?, receiverAdd=?, receiverName=?, receiverPhone=? WHERE oid=?";
        jdbcTemplate.update(sql, order.getState(), order.getReceiverAdd(), order.getReceiverName(), order.getReceiverPhone(),
                order.getOid());
    }
}
