package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    Product createOrUpdateProduct(UUID id, Product product);
    List<Product> getAllProducts();
    List<Product> getAllLaptops();
    Product getProductById(UUID id);
    List<Product> searchProducts(String keyword); // Thêm phương thức cho tìm kiếm sản phẩm

    void deleteProduct(UUID id); // Thêm phương thức cho xóa sản phẩm
}