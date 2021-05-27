package br.com.liferay.models;

import br.com.liferay.models.enums.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private boolean imported;
    private String type;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "cd_product_order")
    private ProductOrder order;

    public Product() {

    }

    public Product(String name, boolean isImported, ProductType productType, BigDecimal price) {
        this.name = name;
        this.imported = isImported;
        this.type = productType.getType();
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        imported = imported;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrder(ProductOrder order) {
        this.order = order;
    }

    public ProductType getType() {
        return ProductType.returnProductType(this.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(ProductType productType) {
        this.type = productType.getType();
    }
}
