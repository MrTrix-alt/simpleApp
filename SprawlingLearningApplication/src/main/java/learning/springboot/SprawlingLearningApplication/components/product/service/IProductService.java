package learning.springboot.SprawlingLearningApplication.components.product.service;


import learning.springboot.SprawlingLearningApplication.components.product.entity.Product;

import java.util.List;

public interface IProductService {
    void deleteProductById(long id);
    void saveProduct(Product product);
    Product findById(long id);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    List<Product> findByName(String name);
}
