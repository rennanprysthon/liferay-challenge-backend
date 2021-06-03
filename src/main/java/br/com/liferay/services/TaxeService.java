package br.com.liferay.services;

import br.com.liferay.models.Product;
import br.com.liferay.services.sales.ImportedSalesImp;
import br.com.liferay.services.sales.MusicSalesImp;
import br.com.liferay.services.sales.OtherSalesImp;
import br.com.liferay.services.sales.SalesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaxeService {

    @Autowired
    List<SalesInterface> sales;

    @Bean
    public List<SalesInterface> sales() {
        return Arrays.asList(
            new ImportedSalesImp(),
            new MusicSalesImp(),
            new OtherSalesImp()
        );
    }

    public BigDecimal applyTaxes(List<Product> products) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal taxes;

        for (Product product : products) {
            taxes = incrementPrice(product);
            total = total.add(taxes);
        }

        total = total.setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    private BigDecimal incrementPrice(Product product) {
        BigDecimal productPrice = product.getPrice();
        BigDecimal percent = returnTaxePercent(product);
        BigDecimal newPrice = productPrice.multiply(percent).setScale(2, RoundingMode.HALF_UP);

        productPrice = productPrice.add(round(newPrice));

        product.setPrice(productPrice.setScale(2, RoundingMode.HALF_UP));

        return newPrice;
    }

    BigDecimal round(BigDecimal newPrice) {
        double value = newPrice.doubleValue();
        String price = newPrice.toString();
        String lastValue = price.substring(price.length() - 1);

        if(!Arrays.asList("5", "0").contains(lastValue)) {
            return round(newPrice.add(new BigDecimal("0.01")));
        }

        value = Math.rint(value * 20) / 20;
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }


    private BigDecimal returnTaxePercent(Product product) {
        BigDecimal result = BigDecimal.ZERO;

        List<BigDecimal> totalPercent = sales
            .stream()
            .map(p ->  p.apply(product))
            .collect(Collectors.toList());

        for (BigDecimal bigDecimal : totalPercent) {
            result = result.add(bigDecimal);
        }

        return result;
    }
}
