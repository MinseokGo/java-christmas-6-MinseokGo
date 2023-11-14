package christmas.model;

public class Customer {
    private final int visitDate;
    private final Order order;

    public Customer(final int visitDate, final Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }
}