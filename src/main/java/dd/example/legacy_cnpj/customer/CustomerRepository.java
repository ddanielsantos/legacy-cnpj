package dd.example.legacy_cnpj.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.address.city = :city AND c.address.state = :state")
    List<Customer> findByAddressCityAndState(@Param("city") String city, @Param("state") String state);

    List<Customer> findByAddress(Address address);

    @Query("SELECT c FROM Customer c WHERE c.address.street LIKE %:street%")
    List<Customer> findByStreet(@Param("street") String street);

    Customer findByCnpj(@Param("cnpj") Long cnpj);
}