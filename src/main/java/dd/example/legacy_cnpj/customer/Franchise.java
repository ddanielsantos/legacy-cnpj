package dd.example.legacy_cnpj.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Franchise {

    @Id
    private Long cnpj;

    @Column
    private String corporateName;

    public Franchise(Long cnpj, String corporateName) {
        this.cnpj = cnpj;
        this.corporateName = corporateName;
    }

    public Franchise() {
    }

    public Long getCnpj() {
        return cnpj;
    }

    public String getCorporateName() {
        return corporateName;
    }
}