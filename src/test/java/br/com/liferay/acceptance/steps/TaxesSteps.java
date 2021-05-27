package br.com.liferay.acceptance.steps;

import br.com.liferay.models.Product;
import br.com.liferay.models.enums.ProductType;
import br.com.liferay.services.TaxeService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaxesSteps {

    private TaxeService taxeService;

    @Before
    public void setUpService() {
        this.taxeService = new TaxeService();
    }

    List<Product> productList = new ArrayList<>();
    BigDecimal finalValue = BigDecimal.ZERO;
    BigDecimal finalTaxes = BigDecimal.ZERO;
    Product product;

    @Given("one product of type {string}, value {double}, imported {string}")
    public void one_product_of_type_value_imported(String type, Double value, String imported) {
        product = new Product("",imported.equalsIgnoreCase("true"), ProductType.returnProductType(type), new BigDecimal(value));
    }


    @When("calculate percent")
    public void calculate_percent() {
        this.taxeService.applyTaxes(Arrays.asList(product));
    }

    @Then("be equals to {double}")
    public void be_equals_to(Double double1) {
        BigDecimal value = new BigDecimal(double1).setScale(2, RoundingMode.HALF_UP);
        assertEquals(value, product.getPrice());
    }

    @Given("multiply")
    public void multiply(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps();

        for (Map<String, String> map : maps) {
            String name = map.get("name");
            ProductType type = ProductType.returnProductType(map.get("type"));
            BigDecimal value = new BigDecimal(map.get("value"));
            Boolean imported = map.get("imported").equalsIgnoreCase("true");

            productList.add(new Product(name, imported, type, value));
        }
    }

    @When("calculate all values")
    public void calculateAllValues() {
        finalTaxes = this.taxeService.applyTaxes(productList);
        finalValue = this.productList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Then("sales taxes should be <{string}> and total value should be <{string}>")
    public void salesTaxesShouldBeAndTotalValueShouldBe(String salesTaxes, String valueFinal) {
        assertEquals(new BigDecimal(salesTaxes), finalTaxes);
        assertEquals(new BigDecimal(valueFinal), finalValue);
    }
}
