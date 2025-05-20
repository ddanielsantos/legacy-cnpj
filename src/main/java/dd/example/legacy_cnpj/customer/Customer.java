package dd.example.legacy_cnpj.customer;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    private Long id;

    @Column
    private final String name;

    @Column
    private final Long cnpj;

    @ManyToOne
    private Address address;

    @ManyToOne
    @JoinColumn(name = "franchise_cnpj", referencedColumnName = "cnpj")
    private Franchise franchise;

    public Customer(String name, Long cnpj, Address address, Franchise franchise) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.franchise = franchise;
    }

    public String getName() {
        return name;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public Address getAddress() {
        return address;
    }

    public Franchise getFranchise() {
        return franchise;
    }
}
