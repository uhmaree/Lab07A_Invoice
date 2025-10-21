public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-15s %5d %10.2f %10.2f",
                product.getName(), quantity, product.getUnitPrice(), getSubtotal());
    }
}
