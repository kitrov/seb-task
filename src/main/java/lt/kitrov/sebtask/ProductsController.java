package lt.kitrov.sebtask;

import lt.kitrov.sebtask.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static lt.kitrov.sebtask.model.ProductType.*;

@RestController
public class ProductsController {

    @RequestMapping("/products")
    public List<Product> productsSuggestion(@RequestParam(value = "age", required = true) int age,
                                            @RequestParam(value = "student", required = true) boolean student,
                                            @RequestParam(value = "income", required = true) int income) {

        List<Product> productList = new ArrayList<>();
        if (age < 0 || income < 0) return productList;

        if (age > 17) {
            suggestProductByAdultIncome(productList, income);

            if (age > 65) {
                productList.add(new Product(SENIOR_ACCOUNT));
            }

            if (student) {
                productList.add(new Product(STUDENT_ACCOUNT));
            }
        } else {
            productList.add(new Product(JUNIOR_SAVER_ACCOUNT));
        }
        return productList;
    }

    private void suggestProductByAdultIncome(List<Product> productList, int income) {
        if (income > 40000) {
            productList.add(new Product(CURRENT_ACCOUNT_PLUS));
            productList.add(new Product(GOLD_CREDIT_CARD));
        } else if (income > 12000) {
            productList.add(new Product(CREDIT_CARD));
        } else {
            productList.add(new Product(CURRENT_ACCOUNT));
            productList.add(new Product(DEBIT_CARD));
        }
    }
}
