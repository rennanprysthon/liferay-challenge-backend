package br.com.liferay.services.sales;

import br.com.liferay.models.Product;

import java.math.BigDecimal;

public interface SalesInterface {

    BigDecimal apply(Product product);

}
