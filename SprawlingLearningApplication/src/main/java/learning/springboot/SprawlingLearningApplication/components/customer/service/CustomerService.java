package learning.springboot.SprawlingLearningApplication.components.customer.service;

import learning.springboot.SprawlingLearningApplication.components.customer.entity.Customer;
import learning.springboot.SprawlingLearningApplication.components.customer.repostiory.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer findById(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            return null;
        }
        return customerOptional.get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers.stream().
                filter(customer -> customer.getName().equals(name))
                .collect(Collectors.toList());
    }
}
