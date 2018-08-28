package controller;

import entity.Category;
import entity.PageBean;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CategoryService;
import service.ProductService;
import utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siren Chen.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductServlet {
    @Autowired
    private ProductService pService;
    @Autowired
    private CategoryService cService;


    @RequestMapping(value = "/showProductInfo")
    public String showProductInfo(Model model, HttpServletRequest request, HttpServletResponse response, String pid)
            throws SQLException {
        Cookie visitList = CookieUtil.getCookieByName("visitList", request.getCookies());

        Product product = pService.findProductById(pid);
        visitList = pService.add2VisitList(pid, visitList);


        response.addCookie(visitList);
        model.addAttribute("product", product);
        return "product/product_info";
    }


    @RequestMapping(value = "/showProductList", method = RequestMethod.GET)
    public String showProductList(Model model, HttpServletRequest request, Integer cid, Integer curPage)
            throws SQLException {
        Cookie visitList = CookieUtil.getCookieByName("visitList", request.getCookies());

        Category category = cService.findCategoryById(cid);
        PageBean pageBean = pService.findProductsByCategory(curPage, cid);
        String[] products = pService.getVisitList(visitList);

        List<Product> visited = new ArrayList<>();
        if (products != null) {
            for (String product : products) {
                visited.add(pService.findProductById(product));
            }
        }

        model.addAttribute("category", category);
        model.addAttribute("visitList", visited);
        model.addAttribute("page", pageBean);
        return "product/product_list";
    }


    /**
     * add a product.  Still in BETA edition...
     * @param request
     * @param response
     * @return
     */
//    public String addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException,
//            IOException, SQLException, InvocationTargetException, IllegalAccessException, FileUploadException {
//        String id = UUIDUtil.genId();
//
//        HashMap<String, Object> map = new HashMap<>();
//        Product product = new Product();
//
//        DiskFileItemFactory fac = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(fac);
//
//        List<FileItem> list = upload.parseRequest(request);
//        for (FileItem fileItem : list) {
//            if (fileItem.isFormField()) {
//
//                map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
//
//            } else {
//
//                String path = this.getServletContext().getRealPath("/img");
//
//                InputStream is = fileItem.getInputStream();
//
//                FileOutputStream fs = new FileOutputStream(new File(path, id+".jpg"));
//
//                IOUtils.copy(is, fs);
//                is.close();
//                fs.close();
//                fileItem.delete();
//
//                map.put(fileItem.getFieldName(), "/img/"+id+".jpg");
//            }
//        }
//
//        BeanUtils.populate(product, map);
//        product.setPdate(new Date());
//        product.setPid(id);
//
//        pService.addProduct(product);
//
//        return null;
//    }
}
