package sahe.com.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahe.com.productservice.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
    List<Product> findByActive(Boolean active);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryAndActive(String category, Boolean active);
}