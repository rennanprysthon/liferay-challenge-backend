package br.com.liferay.services.sales;

import br.com.liferay.models.Product;
import br.com.liferay.models.enums.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MusicSalesImp implements SalesInterface{
    @Override
    public BigDecimal apply(Product product) {

        if (product.getType().equals(ProductType.MUSIC))
            return new BigDecimal("0.15");

        return BigDecimal.ZERO;
    }
}
