package dev.thillainirmal.shopops.catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List; import java.util.Optional; import java.util.UUID;
public interface ProductRepository extends JpaRepository<Product,UUID>{
 Optional<Product> findBySkuIgnoreCase(String sku);
 @Query("select p from Product p where lower(p.name) like lower(concat('%',:q,'%')) or lower(p.description) like lower(concat('%',:q,'%')) or lower(p.category) like lower(concat('%',:q,'%')) order by p.stockQuantity desc")
 List<Product> search(@Param("q") String query);
}
