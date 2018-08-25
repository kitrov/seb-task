package lt.kitrov.sebtask.model;

public class Product {

    private String name;

    public Product(ProductType productType) {
        this.name = productType.toString();
    }

    public String getName() {
        return name;
    }
}
