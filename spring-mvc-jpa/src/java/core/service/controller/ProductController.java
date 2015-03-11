/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.service.controller;

import core.entity.Product;
import core.service.dao.CategoryDAO;
import core.service.dao.ProductDAO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAll(ModelMap model) {
        model.addAttribute("products", productDAO.getAll());
        return "products";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String viewInsert(ModelMap model) {
        model.addAttribute("categories", categoryDAO.getAll());
        return "product_insert";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String save(HttpServletRequest request) {
        String code = request.getParameter("txtCode");
        String name = request.getParameter("txtName");
        double price = Double.valueOf(request.getParameter("txtPrice"));
        String description = request.getParameter("txtDesc");
        long categoryId = Long.valueOf(request.getParameter("txtCategory"));
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(categoryDAO.getById(categoryId));
        productDAO.insert(product);
        return "redirect:/product";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String viewEdit(HttpServletRequest request, ModelMap model) {
        long id = Long.valueOf(request.getParameter("id"));
        model.addAttribute("product", productDAO.getById(id));
        model.addAttribute("categories", categoryDAO.getAll());
        return "product_edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(HttpServletRequest request) {
        long id = Long.valueOf(request.getParameter("id"));
        String code = request.getParameter("txtCode");
        String name = request.getParameter("txtName");
        double price = Double.valueOf(request.getParameter("txtPrice"));
        String description = request.getParameter("txtDesc");
        long categoryId = Long.valueOf(request.getParameter("txtCategory"));
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(categoryDAO.getById(categoryId));
        productDAO.update(product);
        return "redirect:/product";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String delete(HttpServletRequest request) {
        long id = Long.valueOf(request.getParameter("id"));
        productDAO.delete(productDAO.getById(id));
        return "redirect:/product";
    }
}
