/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.service.controller;

import core.entity.Category;
import core.service.dao.CategoryDAO;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAll(ModelMap model) {
        model.addAttribute("categories", categoryDAO.getAll());
        return "categories";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String viewInsert() {
        return "category_insert";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String save(HttpServletRequest request) {
        String name = request.getParameter("txtName");
        Category category = new Category();
        category.setName(name);
        categoryDAO.insert(category);
        return "redirect:/category";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String viewEdit(HttpServletRequest request, ModelMap model) {
        long id = Long.valueOf(request.getParameter("id"));
        model.addAttribute("category", categoryDAO.getById(id));
        return "category_edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(HttpServletRequest request) {
        long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("txtName");
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDAO.update(category);
        return "redirect:/category";
    }

    public String delete(HttpServletRequest request) {
        long id = Long.valueOf(request.getParameter("id"));
        categoryDAO.delete(categoryDAO.getById(id));
        return "redirect:/category";
    }
}
