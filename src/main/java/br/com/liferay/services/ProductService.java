package br.com.liferay.services;

import br.com.liferay.dto.ResponseProducts;
import br.com.liferay.models.Product;
import br.com.liferay.models.ProductOrder;
import br.com.liferay.repository.ProductOrderRepository;
import br.com.liferay.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private TaxeService taxeService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Optional<ResponseProducts> createProducts(List<Product> products) {
        BigDecimal salesTaxes = taxeService.applyTaxes(products).setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalValues = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        ResponseProducts responseProducts = new ResponseProducts(products, salesTaxes.doubleValue(), finalValues.doubleValue());

        if (storeProducts(responseProducts))
            return Optional.of(responseProducts);

        return Optional.empty();
    }

    private boolean storeProducts(ResponseProducts responseProducts) {
        List<Product> producs = productRepository.saveAll(responseProducts.getProducts());
        ProductOrder productOrder = new ProductOrder(responseProducts.getSalesTaxes(), responseProducts.getFinalValue(), producs);

        productOrderRepository.save(productOrder);

        return true;
    }

    public List<Product> returnAll(Long order) {
        if (order != null) {
            Optional<ProductOrder> productOrder = returnOrder(order);
            if (productOrder.isPresent()) return new ArrayList<>(productOrder.get().getProducts());
        }

        return this.productRepository.findAll();
    }

    public Optional<ProductOrder> returnOrder(Long order) {
        return productOrderRepository.findById(order);
    }

}
