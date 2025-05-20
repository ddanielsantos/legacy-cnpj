package dd.example.legacy_cnpj.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> all = customerRepository.findAll();

        if (all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Customer> getCustomerByCnpj(@PathVariable Long cnpj) {
        var customer = customerRepository.findByCnpj(cnpj);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    // Method to find customers by address (city and state)
    @GetMapping("/by-location")
    public List<Customer> getCustomersByLocation(
            @RequestParam String city,
            @RequestParam String state) {
        return customerRepository.findByAddressCityAndState(city, state);
    }

    // Method to find customers by street
    @GetMapping("/by-street")
    public List<Customer> getCustomersByStreet(@RequestParam String street) {
        return customerRepository.findByStreet(street);
    }

    // Method to create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
}
