package br.com.liferay.services;

import br.com.liferay.models.Product;
import br.com.liferay.models.enums.ProductType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxeServiceTest {
    @Autowired
    private TaxeService taxeService = new TaxeService();

    @Test
    public void shouldReturnValueFromNonTaxedProducts() {
        Product book = new Product("Food", true, ProductType.FOOD, new BigDecimal("11.25"));
        taxeService.applyTaxes(Collections.singletonList(book));
        assertEquals(new BigDecimal("11.85"), book.getPrice());
    }

    @Test
    public void shouldRoundUp() {
        BigDecimal bigDecimal = new BigDecimal("0.12");
        BigDecimal excpected = new BigDecimal("0.15");
        BigDecimal result = BigDecimal.ZERO;
        result = taxeService.round(bigDecimal);

        assertEquals(excpected, result);
    }
}
