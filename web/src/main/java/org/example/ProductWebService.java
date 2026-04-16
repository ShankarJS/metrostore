package org.example;

import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.dto.ProductListResponse;
import org.example.dto.ProductRequest;
import org.example.dto.ProductResponse;
import org.example.service.ProductService;

@WebService
public class ProductWebService {
    @EJB
    private ProductService productService;

    @WebMethod
    public ProductResponse addProduct(ProductRequest request){
        return productService.addProduct(request);
    }

    @WebMethod
    @WebResult(name="products")
    public ProductListResponse getProducts(){
        return productService.getProducts();
    }

    @WebMethod
    @WebResult(name="product")
    public ProductResponse getProductById(int id){
        return productService.getProductById(id);
    }

    @WebMethod
    @WebResult(name="product")
    public ProductResponse updateProduct(ProductRequest request){
        return productService.updateProduct(request);
    }

    @WebMethod
    public String deleteProduct(int id){
        return productService.deleteProduct(id);
    }
}
