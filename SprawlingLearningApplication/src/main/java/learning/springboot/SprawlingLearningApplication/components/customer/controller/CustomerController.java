package learning.springboot.SprawlingLearningApplication.components.customer.controller;

import learning.springboot.SprawlingLearningApplication.components.customer.entity.Customer;
import learning.springboot.SprawlingLearningApplication.components.customer.service.ICustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/showCustomers")
    public String findCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
//        logger.debug("Customers:" + customers);
        return "customer/showCustomers";
    }

    @PostMapping("/addCustomer")
    public String addUser(Customer customer, Model model) {
        logger.debug("Customer new: " + customer);
        customerService.saveCustomer(customer);
        return "redirect:/showCustomers ";
    }

    @GetMapping("/addingCustomer")
    public String showAddForm(Customer customer) {
        return "customer/addCustomer";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") long id, Model model) {
        System.out.println("Model: " + model);
        Customer customer = customerService.findById(id);
        logger.debug("Found customer: " + customer);
        model.addAttribute("customer", customer);
        return "customer/editCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    //@PathVariable("id") long id
    public String updateCustomer(Customer customer) {
        logger.debug("Updating customer: " + customer);
        customerService.saveCustomer(customer);
        return "redirect:/showCustomers";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomerByName(@PathVariable("id") long id, Model model) {
        customerService.deleteCustomerById(id);
        return "redirect:/showCustomers";
    }

    @GetMapping("/searchCustomers/{name}")
    public String searchCustomerByName(@RequestParam("name") String name, Model model) {
        logger.debug("Searching customer by name: " + name);
        List<Customer> foundCustomers = customerService.findByName(name);
        logger.debug("Found customers: " + foundCustomers);
        model.addAttribute("customers", foundCustomers);
        return "customer/showCustomers";
    }
}
