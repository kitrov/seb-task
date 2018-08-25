package lt.kitrov.sebtask.model;

public enum ProductType {
    CURRENT_ACCOUNT("Current Account"),
    CURRENT_ACCOUNT_PLUS("Current Account Plus"),
    JUNIOR_SAVER_ACCOUNT("Junior Saver Account"),
    STUDENT_ACCOUNT("Student Account"),
    SENIOR_ACCOUNT("Senior Account"),
    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    GOLD_CREDIT_CARD("Gold Credit Card");

    private String name;

    ProductType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
