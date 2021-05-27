package br.com.liferay.models;

import br.com.liferay.models.enums.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long id;
    private BigDecimal taxes;
    private BigDecimal total;
    @OneToMany
    @JoinColumn(name = "cd_product_order")
    private Set<Product> products = new HashSet<>();

    public ProductOrder() {
    }

    public ProductOrder(Double taxes, Double total, List<Product> products) {
        this.taxes = new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP);
        this.total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        this.products.addAll(products);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<Product> getProducts() {
        return products;
    }
}

