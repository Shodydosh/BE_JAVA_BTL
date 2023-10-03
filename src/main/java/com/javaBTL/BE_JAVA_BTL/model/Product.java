package com.javaBTL.BE_JAVA_BTL.model;
import java.util.UUID;

public class Product {
    private UUID id;
    private String retailer;
    private String img_url;
    private String name;
    private String price;
    private String url;
    private String category;

    // Constructors
    public Product() {
        this.id = UUID.randomUUID(); // Tạo UUID tự động khi khởi tạo đối tượng Product
    }

    public Product(String retailer, String img_url, String name, String price, String url, String category) {
        this.id = UUID.randomUUID(); // Tạo UUID tự động khi khởi tạo đối tượng Product
        this.retailer = retailer;
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.url = url;
        this.category = category;
    }

    // Getter and Setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString method to print the object
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", retailer='" + retailer + '\'' +
                ", img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
