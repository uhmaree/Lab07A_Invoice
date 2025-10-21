import java.util.ArrayList;

public class Invoice {
    private Customer customer;
    private ArrayList<LineItem> items = new ArrayList<>();

    public Invoice(Customer customer) {
        this.customer = customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(LineItem::getSubtotal).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== INVOICE =====\n");
        sb.append(customer.toString()).append("\n\n");
        sb.append(String.format("%-15s %5s %10s %10s\n", "Item", "Qty", "Price", "Total"));
        sb.append("---------------------------------------------------\n");
        for (LineItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("---------------------------------------------------\n");
        sb.append(String.format("AMOUNT DUE: $%.2f\n", getTotal()));
        return sb.toString();
    }
}
