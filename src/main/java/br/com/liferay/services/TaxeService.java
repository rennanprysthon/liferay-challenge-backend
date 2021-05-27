package br.com.liferay.services;

import br.com.liferay.models.Product;
import br.com.liferay.models.enums.ProductType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Service
public class TaxeService {
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

    private BigDecimal round(BigDecimal newPrice) {
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
        BigDecimal totalPercent = BigDecimal.ZERO;
        if (product.isImported()){
            totalPercent = totalPercent.add(new BigDecimal("0.05"));
        }
        if (product.getType().equals(ProductType.OTHER)){
            totalPercent = totalPercent.add(new BigDecimal("0.10"));
        }

        return totalPercent;
    }
}
