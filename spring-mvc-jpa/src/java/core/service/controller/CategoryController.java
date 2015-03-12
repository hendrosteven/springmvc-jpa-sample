/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.service.controller;

import core.entity.Category;
import core.service.dao.CategoryDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String viewAll(HttpServletRequest request,ModelMap model) {
        model.addAttribute("categories", categoryDAO.getAll());
        model.addAttribute("errors", request.getParameter("errors"));
        return "categories_json";
    }
    
    @RequestMapping(method = RequestMethod.GET,value="/json")
    @ResponseBody
    public List<Category> getJSon() {
        return categoryDAO.getAll();
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

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String delete(HttpServletRequest request, ModelMap model) {
        long id = Long.valueOf(request.getParameter("id"));
        try {
            categoryDAO.delete(categoryDAO.getById(id));
        } catch (Exception ex) {
            model.addAttribute("errors", "Gagal dihapus");
        }
        return "redirect:/category";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/json")
    @ResponseBody
    public boolean deleteJSon(HttpServletRequest request) {
        long id = Long.valueOf(request.getParameter("id"));
        try {
            categoryDAO.delete(categoryDAO.getById(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
