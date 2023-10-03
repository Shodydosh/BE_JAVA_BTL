package com.javaBTL.BE_JAVA_BTL.controller;

import com.javaBTL.BE_JAVA_BTL.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private List<Product> productList = new ArrayList<>();

    // Endpoint để lấy tất cả sản phẩm
    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }

    // Endpoint để lấy tất cả sản phẩm là laptop
    @GetMapping("/laptop")
    public List<Product> getAllLaptops() {
        List<Product> laptops = new ArrayList<>();
        for (Product product : productList) {
            if ("laptop".equalsIgnoreCase(product.getCategory())) {
                laptops.add(product);
            }
        }
        return laptops;
    }

    // Endpoint để lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Trả về null nếu không tìm thấy sản phẩm
    }

    // Endpoint để thêm sản phẩm (ADMIN)
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productList.add(product);
    }

    // Endpoint để cập nhật sản phẩm theo ID (ADMIN)
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.set(i, updatedProduct);
                return;
            }
        }
    }

    // Endpoint để xóa sản phẩm theo ID (ADMIN)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productList.removeIf(product -> product.getId() == id);
    }
}
