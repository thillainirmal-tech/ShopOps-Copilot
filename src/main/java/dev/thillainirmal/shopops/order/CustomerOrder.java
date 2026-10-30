package dev.thillainirmal.shopops.order;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="customer_orders")
public class CustomerOrder {
 @Id private UUID id; @Column(nullable=false,length=120) private String customerEmail; @Column(nullable=false,length=48) private String sku; @Column(nullable=false) private int quantity; @Column(nullable=false,precision=12,scale=2) private BigDecimal total; @Column(nullable=false,length=20) private String status; @Column(nullable=false) private Instant createdAt;
 protected CustomerOrder(){} public CustomerOrder(UUID id,String email,String sku,int quantity,BigDecimal total,Instant createdAt){this.id=id;this.customerEmail=email;this.sku=sku;this.quantity=quantity;this.total=total;this.status="RESERVED";this.createdAt=createdAt;}
 public UUID getId(){return id;} public String getCustomerEmail(){return customerEmail;} public String getSku(){return sku;} public int getQuantity(){return quantity;} public BigDecimal getTotal(){return total;} public String getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
}
