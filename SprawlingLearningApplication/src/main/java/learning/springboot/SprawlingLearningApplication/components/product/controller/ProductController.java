package learning.springboot.SprawlingLearningApplication.components.product.controller;

import learning.springboot.SprawlingLearningApplication.components.product.entity.Product;
import learning.springboot.SprawlingLearningApplication.components.product.service.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    @GetMapping("/showProducts")
    public String findProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/showProducts";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model) {
        logger.debug("Proudct is: " + product);
        productService.saveProduct(product);
        return "redirect:/showProducts";
    }

    @GetMapping("/addingProduct")
    public String showAddForm(Product product) {
        return "product/addProduct";
    }

    @GetMapping("/searchProductByCategory/{category}")
    public String findProductsByCategory(@RequestParam("category") String category, Model model) {
        List<Product> products = productService.findByCategory(category);
        model.addAttribute("products", products);
        return "product/showProducts";
    }

    @GetMapping("/searchProductByName/{name}")
    public String findProductsByName(@RequestParam("name") String name, Model model) {
        logger.debug("Name: " + name);
        List<Product> products = productService.findByName(name);
        model.addAttribute("products", products);
        return "product/showProducts";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        logger.debug("Found product: " + product);
        model.addAttribute("product", product);
        return "product/editProduct";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(Product product) {
        logger.debug("Updating product: " + product);
        productService.saveProduct(product);
        return "redirect:/showProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/showProducts";
    }
}
