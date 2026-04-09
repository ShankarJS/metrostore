package org.example.service;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.persistence.entity.Product;

import java.util.Date;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    public void saveProduct(){
        Product p = new Product();
        p.setBrand("Samsung");
        p.setModel("A17");
        p.setPrice(21000);
        p.setStockQuantity(5);
        p.setInsert_dt(new Date());
        p.setUpdate_dt(new Date());
        System.out.println("Saving product...");
        em.persist(p);
    }
}
