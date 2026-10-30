package dev.thillainirmal.shopops.web;
import dev.thillainirmal.shopops.ai.CommerceAssistant; import dev.thillainirmal.shopops.catalog.ProductRepository; import dev.thillainirmal.shopops.order.CustomerOrder; import dev.thillainirmal.shopops.order.OrderService; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api")
public class CommerceController {
 private final ProductRepository products; private final CommerceAssistant assistant; private final OrderService orders;
 public CommerceController(ProductRepository products,CommerceAssistant assistant,OrderService orders){this.products=products;this.assistant=assistant;this.orders=orders;}
 @GetMapping("/products") public List<ProductResponse> products(@RequestParam(defaultValue="") @Size(max=120) String q){return products.search(q).stream().limit(25).map(ProductResponse::from).toList();}
 @PostMapping("/assistant") public AssistantResponse assistant(@Valid @RequestBody AssistantRequest r){return new AssistantResponse(r.conversationId(),assistant.answer(r.conversationId(),r.message()));}
 @PostMapping("/orders") @ResponseStatus(HttpStatus.CREATED) public CustomerOrder order(@Valid @RequestBody CreateOrder r){return orders.reserve(r.customerEmail(),r.sku(),r.quantity());}
 public record AssistantRequest(@NotBlank @Size(max=80) String conversationId,@NotBlank @Size(max=2000) String message){} public record AssistantResponse(String conversationId,String answer){}
 public record CreateOrder(@Email @NotBlank String customerEmail,@NotBlank @Size(max=48) String sku,@Min(1) @Max(20) int quantity){}
 public record ProductResponse(String sku,String name,String description,String category,java.math.BigDecimal price,int stock){static ProductResponse from(dev.thillainirmal.shopops.catalog.Product p){return new ProductResponse(p.getSku(),p.getName(),p.getDescription(),p.getCategory(),p.getPrice(),p.getStockQuantity());}}
}
