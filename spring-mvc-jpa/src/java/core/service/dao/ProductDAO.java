/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.service.dao;

import core.entity.Product;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository("productDAO")
@Transactional
public class ProductDAO extends GeneralDAO {

    public Product getById(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> getAll() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

    public List<Product> getByName(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :param")
                .setParameter("param", "%" + name + "%").getResultList();
    }
}
