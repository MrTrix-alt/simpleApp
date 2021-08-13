package learning.springboot.SprawlingLearningApplication.components.product.repository;

import learning.springboot.SprawlingLearningApplication.components.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    List<Product> findDistinctByCategory(String category);
    List<Product> findByName(String name);
}
