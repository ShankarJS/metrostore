package org.example.service;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.dto.ProductListResponse;
import org.example.dto.ProductRequest;
import org.example.dto.ProductResponse;
import org.example.persistence.entity.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductService {

    @PersistenceContext
    private EntityManager em;

//    public void saveProduct(){
//        Product p = new Product();
//        p.setBrand("Samsung");
//        p.setModel("A17");
//        p.setPrice(21000);
//        p.setStockQuantity(5);
//        p.setInsert_dt(new Date());
//        p.setUpdate_dt(new Date());
//        System.out.println("Saving product...");
//        em.persist(p);
//    }

    public ProductResponse addProduct(ProductRequest request) {
        // Basic validation (very important)
        if (request.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (request.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        Product product = new Product();
        product.setBrand(request.getBrand());
        product.setModel(request.getModel());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Date now = new Date();
        product.setInsert_dt(now);
        product.setUpdate_dt(now);

        //perist
        em.persist(product);

        //convert entity to response
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setBrand(product.getBrand());
        response.setModel(product.getModel());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());

        return response;
    }

    public ProductListResponse getProducts() {
        Query query = em.createQuery("select p from Product p");
        List<Product> productList = query.getResultList();

        List<ProductResponse> productResponseList = new ArrayList<>();

        if (productList!=null && !productList.isEmpty()){
            for (Product product : productList){
                if (product!=null){
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setModel(product.getModel());
                    productResponse.setBrand(product.getBrand());
                    productResponse.setStockQuantity(product.getStockQuantity());
                    productResponseList.add(productResponse);
                }
            }
        }
        ProductListResponse finalResponse = new ProductListResponse();
        finalResponse.setProducts(productResponseList);
        return finalResponse;
    }

    public ProductResponse getProductById(int id) {
        ProductResponse productResponse = new ProductResponse();
        try{
            Product product = em.find(Product.class, id);
            if(product!=null){
                productResponse.setId(product.getId());
                productResponse.setPrice(product.getPrice());
                productResponse.setModel(product.getModel());
                productResponse.setBrand(product.getBrand());
                productResponse.setStockQuantity(product.getStockQuantity());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return productResponse;
    }

    public ProductResponse updateProduct(ProductRequest request) {
        if(request == null || request.getId() == null){
            throw new RuntimeException("Product id is required for update");
        }

        Product product = em.find(Product.class, request.getId());

        if(product == null){
            throw new RuntimeException("Product not found for id: "+request.getId());
        }

        if(request.getBrand()!=null){
            product.setBrand(request.getBrand());
        }

        if(request.getModel()!=null){
            product.setModel(request.getModel());
        }

        if(request.getPrice()!=null){
            product.setPrice(request.getPrice());
        }

        if(request.getStockQuantity()!=null){
            product.setStockQuantity(request.getStockQuantity());
        }

        product.setUpdate_dt(new Date());

        // No need for em.merge() because entity is managed

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setBrand(product.getBrand());
        response.setModel(product.getModel());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());

        return response;
    }

    public String deleteProduct(int id) {
        Product product = em.find(Product.class, id);

        if(product == null){
            throw new RuntimeException("Product not found for id: "+id);
        }

        em.remove(product);

        return "Product deleted successfully for id: " +id;
    }
}
