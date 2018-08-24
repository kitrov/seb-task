package lt.kitrov.sebtask;

import lt.kitrov.sebtask.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @RequestMapping("/products")
    public List<Product> productsSuggestion(@RequestParam(value = "age", required = true) int age,
                                            @RequestParam(value = "student", required = true) boolean student,
                                            @RequestParam(value = "income", required = true) int income) {
        return new ArrayList<>();
    }
}
