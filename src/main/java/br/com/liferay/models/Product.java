package br.com.liferay.models;

import br.com.liferay.models.enums.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private Long id;
    private String name;
    private boolean isImported;
    private String productType;
    private BigDecimal price;

    public Product(String name, boolean isImported, ProductType productType, BigDecimal price) {
        this.name = name;
        this.isImported = isImported;
        this.productType = productType.getType();
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
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public ProductType getProductType() {
        return ProductType.returnProductType(this.productType);
    }

    public void setProductType(ProductType productType) {
        this.productType = productType.getType();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
