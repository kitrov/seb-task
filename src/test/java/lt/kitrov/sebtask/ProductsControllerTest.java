package lt.kitrov.sebtask;

import lt.kitrov.sebtask.controller.ProductsController;
import lt.kitrov.sebtask.model.Product;
import lt.kitrov.sebtask.model.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static lt.kitrov.sebtask.model.ProductType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    private static void assertIfContainsProduct(List<Product> productList, ProductType productType) {
        assertTrue(productList.stream().anyMatch(product -> product.getName().equals(productType.toString())));
    }

    private void assertIfProductInSuggestion(int age, boolean isStudent, int income, ProductType productType) {
        List<Product> result = productsController.productsSuggestion(age, isStudent, income);
        assertIfContainsProduct(result, productType);
    }

    @Test
    public void productsSuggestion_currentAccount() {
        assertIfProductInSuggestion(18, false, 10, CURRENT_ACCOUNT);
    }

    @Test
    public void productsSuggestion_currentAccountPlusAndGoldCreditCard() {
        assertIfProductInSuggestion(35, false, 40001, CURRENT_ACCOUNT_PLUS);
    }

    @Test
    public void productsSuggestion_juniorSaverAccount() {
        assertIfProductInSuggestion(15, true, 0, JUNIOR_SAVER_ACCOUNT);
    }

    @Test
    public void productsSuggestion_studentAccount() {
        assertIfProductInSuggestion(18, true, 0, STUDENT_ACCOUNT);
    }

    @Test
    public void productsSuggestion_seniorAccount() {
        assertIfProductInSuggestion(68, false, 0, SENIOR_ACCOUNT);
    }

    @Test
    public void productsSuggestion_debitCard() {
        assertIfProductInSuggestion(21, false, 1000, DEBIT_CARD);
    }

    @Test
    public void productsSuggestion_creditCard() {
        assertIfProductInSuggestion(25, false, 12001, CREDIT_CARD);
    }

    @Test
    public void productsSuggestion_goldCreditCard() {
        assertIfProductInSuggestion(45, false, 40001, GOLD_CREDIT_CARD);
    }

    @Test
    public void productsSuggestion_negativeIncomeOrAge() {
        List<Product> result = productsController.productsSuggestion(-1, false, 0);
        assertTrue(result.isEmpty());
        result = productsController.productsSuggestion(18, false, -1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void productsSuggestion_adultHighIncome() {
        List<Product> result = productsController.productsSuggestion(18, false, 44000);
        assertEquals(4, result.size());
        assertIfContainsProduct(result, CURRENT_ACCOUNT);
        assertIfContainsProduct(result, CURRENT_ACCOUNT_PLUS);
        assertIfContainsProduct(result, CREDIT_CARD);
        assertIfContainsProduct(result, GOLD_CREDIT_CARD);
    }

    @Test
    public void productsSuggestion_adultLowIncome() {
        List<Product> result = productsController.productsSuggestion(18, false, 10000);
        assertEquals(2, result.size());
        assertIfContainsProduct(result, CURRENT_ACCOUNT);
        assertIfContainsProduct(result, DEBIT_CARD);
    }
}