package com.javaBTL.BE_JAVA_BTL.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Product {
    private String retailer;
    private String img_url;
    private String name;
    private String price;
    private String url;

    // Constructors
    public Product() {
    }

    public Product(String retailer, String img_url, String name, String price, String url) {
        this.retailer = retailer;
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    // Getter and Setter methods
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

    // toString method to print the object
    @Override
    public String toString() {
        return "Product{" +
                "retailer='" + retailer + '\'' +
                ", img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
