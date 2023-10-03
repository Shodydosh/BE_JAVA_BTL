package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Product;
import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> getAllProduct();
}
