package dev.thillainirmal.shopops.ai;
import dev.thillainirmal.shopops.catalog.ProductRepository; import org.springframework.ai.tool.annotation.Tool; import org.springframework.stereotype.Component;
@Component
public class CatalogTools {
 private final ProductRepository products; public CatalogTools(ProductRepository products){this.products=products;}
 @Tool(description="Search the live product catalog by product name, category, feature or descriptive words. Returns current price and stock; use this instead of inventing products.")
 public java.util.List<ProductFact> searchCatalog(String query){return products.search(query).stream().limit(8).map(ProductFact::from).toList();}
 @Tool(description="Get live price and stock for an exact SKU. Use this before saying an item is available.")
 public ProductFact productBySku(String sku){return products.findBySkuIgnoreCase(sku).map(ProductFact::from).orElse(null);}
 public record ProductFact(String sku,String name,String category,java.math.BigDecimal price,int stock,String description){static ProductFact from(dev.thillainirmal.shopops.catalog.Product p){return new ProductFact(p.getSku(),p.getName(),p.getCategory(),p.getPrice(),p.getStockQuantity(),p.getDescription());}}
}
