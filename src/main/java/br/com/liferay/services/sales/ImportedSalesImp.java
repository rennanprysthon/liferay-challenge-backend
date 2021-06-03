package br.com.liferay.services.sales;

import br.com.liferay.models.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ImportedSalesImp implements SalesInterface{
    @Override
    public BigDecimal apply(Product product) {

        if (product.isImported())
            return new BigDecimal("0.05");

        return BigDecimal.ZERO;
    }
}
