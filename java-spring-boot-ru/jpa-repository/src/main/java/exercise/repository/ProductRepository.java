package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetweenOrderByPriceAsc(int min, int max);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(int min);

    List<Product> findByPriceLessThanEqualOrderByPriceAsc(int max);
    // END
}
