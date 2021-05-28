package br.com.liferay.controllers;

import br.com.liferay.models.ProductOrder;
import br.com.liferay.services.ProductService;
import br.com.liferay.services.exceptions.ProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin("*")
public class ProductOrderController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ProductOrder>> findAll() {
        List<ProductOrder> list = this.productService.returnAllOrders();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProductOrder> findById(@PathVariable("id") Long order) {

        ProductOrder productOrder = this.productService.returnOrder(order)
            .orElseThrow(() -> new ProjectException("Order id invalid!"));

        return ResponseEntity.ok(productOrder);
    }


}
