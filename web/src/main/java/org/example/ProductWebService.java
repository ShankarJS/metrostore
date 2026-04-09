package org.example;

import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.example.service.ProductService;

@WebService
public class ProductWebService {
    @EJB
    private ProductService productService;

    @WebMethod
    public String addProduct(){
        productService.saveProduct();
        return "Product saved successfully";
    }

}
