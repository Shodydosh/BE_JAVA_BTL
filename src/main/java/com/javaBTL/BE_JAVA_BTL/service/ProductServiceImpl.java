package com.javaBTL.BE_JAVA_BTL.service;

import com.javaBTL.BE_JAVA_BTL.model.Product;
import com.javaBTL.BE_JAVA_BTL.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createOrUpdateProduct(UUID id, Product product) {
        if (productRepository.existsById(id)) {
            // Cập nhật sản phẩm theo id
            product.setId(id);
            return productRepository.save(product);
        } else {
            // Tạo sản phẩm mới với id mới
            return productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllLaptops() {
        return productRepository.findProductsByCategory("laptop");
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        // Triển khai logic tìm kiếm sản phẩm dựa trên từ khóa ở đây và trả về danh sách sản phẩm tương ứng
        return productRepository.searchProducts(keyword);
    }


    @Override
    public void deleteProduct(UUID id) {
        // Triển khai logic xóa sản phẩm theo ID ở đây
        productRepository.deleteById(id);
    }
}