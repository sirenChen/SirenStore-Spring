package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Siren Chen.
 */
@Controller
@RequestMapping(value = "/user")
public class UserServlet {

    @Autowired
    private UserService uService;

    @RequestMapping(value = "/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/doRegister")
    public String doRegister(Model model, User user) throws SQLException {

        if (! uService.isUsernameOK(user.getUsername())) {
            model.addAttribute("registerMsg", "username is taken, please choose another username");
            return "user/register";
        }

        uService.register(user);

        model.addAttribute("msg", "Thanks...");
        return "msg";
    }


    @RequestMapping(value = "/doLogin")
    public String doLogin(Model model, HttpSession session, String username, String password)
            throws SQLException {

        User user = uService.login(username, password);

        if (user == null) {
            model.addAttribute("loginMsg", "wrong username or password");
            return "user/login";
        }

        session.setAttribute("user", user);
        model.addAttribute("msg", "Login Success");
        return "msg";
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(Model model, HttpSession session) {
        session.invalidate();

        model.addAttribute("msg", "Logout success");
        return "msg";
    }


    public String doActive (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String activeCode = request.getParameter("activeCode");

        User user = uService.activeUser(activeCode);

        if (user == null) {
            request.setAttribute("msg", "Wrong active code");
        } else {
            request.setAttribute("msg", "active done");
        }

        return "/jsp/msg.jsp";
    }
}
