package dev.thillainirmal.shopops.catalog;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity @Table(name="products")
public class Product {
    @Id private UUID id;
    @Column(nullable=false, unique=true, length=48) private String sku;
    @Column(nullable=false, length=160) private String name;
    @Column(nullable=false, columnDefinition="text") private String description;
    @Column(nullable=false, length=80) private String category;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal price;
    @Column(name="stock_quantity", nullable=false) private int stockQuantity;
    @Version private long version;
    protected Product() {}
    public Product(UUID id,String sku,String name,String description,String category,BigDecimal price,int stockQuantity){this.id=id;this.sku=sku;this.name=name;this.description=description;this.category=category;this.price=price;this.stockQuantity=stockQuantity;}
    public void reserve(int quantity){if(quantity<1)throw new IllegalArgumentException("quantity must be positive");if(stockQuantity<quantity)throw new IllegalStateException("insufficient stock for "+sku);stockQuantity-=quantity;}
    public UUID getId(){return id;} public String getSku(){return sku;} public String getName(){return name;} public String getDescription(){return description;} public String getCategory(){return category;} public BigDecimal getPrice(){return price;} public int getStockQuantity(){return stockQuantity;}
}
