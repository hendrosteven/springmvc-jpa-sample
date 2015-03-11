/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.service.dao;

import core.entity.Category;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDAO extends GeneralDAO {

    public Category getById(long id) {
        return this.em.find(Category.class, id);
    }

    public List<Category> getAll() {
        return this.em.createQuery("SELECT c FROM Category c").getResultList();
    }
}
