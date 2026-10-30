package dev.thillainirmal.shopops.order;
import dev.thillainirmal.shopops.catalog.ProductRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.time.Clock; import java.time.Instant; import java.util.UUID;
@Service
public class OrderService {
 private final ProductRepository products; private final CustomerOrderRepository orders; private final Clock clock;
 public OrderService(ProductRepository products,CustomerOrderRepository orders){this(products,orders,Clock.systemUTC());}
 OrderService(ProductRepository products,CustomerOrderRepository orders,Clock clock){this.products=products;this.orders=orders;this.clock=clock;}
 @Transactional public CustomerOrder reserve(String email,String sku,int quantity){var product=products.findBySkuIgnoreCase(sku).orElseThrow(()->new IllegalArgumentException("unknown SKU: "+sku));product.reserve(quantity);var total=product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));return orders.save(new CustomerOrder(UUID.randomUUID(),email,product.getSku(),quantity,total,Instant.now(clock)));}
}
