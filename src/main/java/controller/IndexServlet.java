package controller;

import entity.Category;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Siren Chen.
 */
@Controller
public class IndexServlet{

    @Autowired
    private ProductService pService;
    @Autowired
    private CategoryService cService;


    @RequestMapping(value = "/home")
    public String index (Model model) throws SQLException {
        List<Product> hotList = pService.findHot();
        model.addAttribute("hotList", hotList);
        return "index";
    }

    @RequestMapping(value = "/showCategory")
    @ResponseBody
    public List<Category> showCategory() throws SQLException {
        return cService.showAllCategory();
    }

    @RequestMapping(value = "/head")
    public String head() {
        return "head";
    }
}
