package controller;

import entity.Product;
import entity.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CartService;
import service.ProductService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by Siren Chen.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartServlet {
    @Autowired
    ProductService pService;
    @Autowired
    CartService cService;

    @RequestMapping(value = "/showCart")
    public String showCart() {
        return "order/cart";
    }

    @RequestMapping(value = "/addToCart")
    public String addToCart(HttpSession session, String pid, Integer quantity) throws SQLException {

        // call service
        Product product = pService.findProductById(pid);
        Cart cart = cService.getCartFrom(session);
        cart = cService.addToCart(product, quantity, cart);

        // redirect
        session.setAttribute("cart", cart);
        return "redirect: showCart.do";
    }

    @RequestMapping(value = "/removeFromCart")
    public String removeFromCart(HttpSession session, String pid) {

        // call service
        Cart cart = cService.getCartFrom(session);
        cart = cService.removeFromCart(pid,cart);

        // redirect
        session.setAttribute("cart", cart);
        return "redirect: showCart.do";
    }
}
