import javax.swing.*;
import java.awt.*;

public class Lab07A_Invoice extends JFrame {
    private JTextField itemField, qtyField, priceField;
    private JTextArea outputArea;
    private Invoice invoice;

    public Lab07A_Invoice() {
        super("Invoice App");
        invoice = new Invoice(new Customer("Amare's Small Appliances", "100 Main Street\nBrooklyn, CA 98765"));

        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 0 Item
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Item:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        itemField = new JTextField(15);
        inputPanel.add(itemField, gbc);

        // Row 1 Quantity
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Quantity:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        qtyField = new JTextField(15);
        inputPanel.add(qtyField, gbc);

        // Row 2 Price
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        priceField = new JTextField(15);
        inputPanel.add(priceField, gbc);

        // Row 3 Add Button
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addItem());
        inputPanel.add(addButton, gbc);

        // Output
        outputArea = new JTextArea(15, 40);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Show Invoice button
        JButton showButton = new JButton("Show Invoice");
        showButton.addActionListener(e -> outputArea.setText(invoice.toString()));
        add(showButton, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addItem() {
        try {
            String name = itemField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            Product p = new Product(name, price);
            LineItem li = new LineItem(p, qty);
            invoice.addItem(li);

            itemField.setText("");
            qtyField.setText("");
            priceField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number for Quantity and Price.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lab07A_Invoice::new);
    }
}
