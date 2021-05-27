package br.com.liferay.dto;

import br.com.liferay.models.Product;

import java.util.List;

public class ResponseProducts {
    private List<Product> products;
    private double salesTaxes;
    private double finalValue;

    public ResponseProducts(List<Product> products, double salesTaxes, double finalValue) {
        this.products = products;
        this.salesTaxes = salesTaxes;
        this.finalValue = finalValue;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public double getFinalValue() {
        return finalValue;
    }
}
