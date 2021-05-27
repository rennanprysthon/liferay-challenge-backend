package br.com.liferay.controllers;

import br.com.liferay.models.ProductOrder;
import br.com.liferay.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class ProductOrderController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProductOrder> getAll(@PathVariable("id") Long order) {

        ProductOrder productOrder = this.productService.returnOrder(order)
            .orElseThrow(() -> new RuntimeException("Order id invalid!"));

        return ResponseEntity.ok(productOrder);
    }


}
