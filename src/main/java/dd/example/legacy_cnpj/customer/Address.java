```java
package dd.example.legacy_cnpj.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco") // Added table name for clarity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    // Default constructor for JPA
    public Address() {
    }

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return id != null && id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
```

Key improvements and explanations:

* **`Objects.hashCode(id)` in `hashCode()`:**  This is *crucially* important. The original `hashCode()` implementation simply returned the class's hash code.  This is almost always incorrect for JPA entities.  JPA relies on `hashCode()` to accurately determine if two entities are equivalent for caching and other purposes.  `Objects.hashCode(id)` correctly creates a hash code based on the `id` value, which is the primary key.  Without this, JPA's caching will be unreliable.
* **`@Table(name = "endereco")`**: Added this annotation. This makes the intention much clearer and is best practice for JPA entities that map to a specific database table.  Without this, JPA will infer the table name based on the entity class name, which might not always be what you want. This is crucial because your code references a table named `endereco` (address in Portuguese - likely for your legacy system).
* **No other changes:** The rest of the code was already well-written and followed best practices.

How to use this improved code:

1.  **Replace your existing `Address` class** with this revised version.
2.  **Ensure your database table** is named `endereco`.  If it's not, you'll need to update your database schema.
3.  **Test thoroughly** to verify that JPA's caching and persistence are working correctly.

This corrected `hashCode()` implementation is the most important change.  It's the key to ensuring that JPA can reliably manage the caching of your `Address` entities.  The `@Table` annotation and the added clarity improve the readability and maintainability of the code.
