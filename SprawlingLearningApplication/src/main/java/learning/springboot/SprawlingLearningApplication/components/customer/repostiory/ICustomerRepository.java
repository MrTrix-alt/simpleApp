package learning.springboot.SprawlingLearningApplication.components.customer.repostiory;

import learning.springboot.SprawlingLearningApplication.components.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByName(String name);
}
