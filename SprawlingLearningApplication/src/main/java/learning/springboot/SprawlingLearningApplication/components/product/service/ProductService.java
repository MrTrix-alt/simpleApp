package learning.springboot.SprawlingLearningApplication.components.product.service;

import learning.springboot.SprawlingLearningApplication.components.product.entity.Product;
import learning.springboot.SprawlingLearningApplication.components.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void saveProduct(Product product) {
        // I'm using java.util.Date because I don't have knowledge how to use date in thymeleaf
        Date addedDate = new Date();
        product.setAddedDate(addedDate);
        productRepository.save(product);
    }

    @Override
    public Product findById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid product ID: " + id);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepository.findDistinctByCategory(category);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}
