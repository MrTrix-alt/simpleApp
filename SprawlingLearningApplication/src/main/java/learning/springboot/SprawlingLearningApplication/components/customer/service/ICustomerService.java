package learning.springboot.SprawlingLearningApplication.components.customer.service;

import learning.springboot.SprawlingLearningApplication.components.customer.entity.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(long id);
    void saveCustomer(Customer customer);
    void deleteCustomerById(long id);
    List<Customer> findCustomersByName(String name);
}
