package controller;

import entity.PageBean;
import entity.User;
import entity.cart.Cart;
import entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by Siren Chen.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderServlet {

    @Autowired
    private OrderService oService;

    @RequestMapping(value = "/genOrder")
    public String genOrder(Model model, HttpSession session) throws SQLException {
        // get data
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null) {
            model.addAttribute("msg", "Please login first");
            return "msg";
        }

        // call service
        Order order = oService.genOrderFromCart(cart, user);

        // redirect
        session.removeAttribute("cart");
        model.addAttribute("order", order);
        return "order/order_info";
    }

    @RequestMapping(value = "/showOrderList")
    public String showOrderList(Model model, HttpSession session, Integer curPage) throws ServletException, IOException,
            SQLException, InvocationTargetException, IllegalAccessException {
        // get data
        User user = (User) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", "Please login first");
            return "msg";
        }

        // call service
        PageBean<Order> page = oService.showOrderList(curPage, user);

        // redirect
        model.addAttribute("page", page);
        return "order/order_list";
    }

    @RequestMapping(value = "/showOrderInfo")
    public String showOrderInfo(Model model, String oid) throws IllegalAccessException, SQLException, InvocationTargetException {

        Order order = oService.showOrderInfo(oid);

        model.addAttribute("order", order);
        return "order/order_info";
    }

    @RequestMapping(value = "/placeOrder")
    public String placeOrder(Model model, String oid, String receiverAdd, String receiverName, String receiverPhone)
            throws SQLException {

        oService.placeOrder(oid, receiverName, receiverAdd, receiverPhone);

        model.addAttribute("msg", "Thank you !!!");
        return "msg";
    }

}
