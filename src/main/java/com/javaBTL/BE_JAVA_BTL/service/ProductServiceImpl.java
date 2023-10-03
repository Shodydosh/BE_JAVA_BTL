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

    // Các phương thức khác cho các chức năng khác có thể được thêm vào ở đây

    // Ví dụ:
    // public void loginUser(LoginRequest loginRequest) {
    //     // Thêm logic đăng nhập ở đây
    // }
    // public void registerUser(RegisterRequest registerRequest) {
    //     // Thêm logic đăng ký ở đây
    // }
    // public void checkout(CheckoutRequest checkoutRequest) {
    //     // Thêm logic thanh toán và gửi email ở đây
    // }
}
