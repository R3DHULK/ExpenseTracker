import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker extends JFrame {

    private List<Expense> expenses;
    private DefaultListModel<String> expenseListModel;
    private JList<String> expenseList;
    private JTextField expenseNameField;
    private JTextField expenseAmountField;

    public ExpenseTracker() {
        setTitle("Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the expense list
        expenses = new ArrayList<>();
        expenseListModel = new DefaultListModel<>();
        expenseList = new JList<>(expenseListModel);
        JScrollPane scrollPane = new JScrollPane(expenseList);
        add(scrollPane, BorderLayout.CENTER);

        // Create the expense input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Expense Name:");
        inputPanel.add(nameLabel);

        expenseNameField = new JTextField();
        inputPanel.add(expenseNameField);

        JLabel amountLabel = new JLabel("Expense Amount:");
        inputPanel.add(amountLabel);

        expenseAmountField = new JTextField();
        inputPanel.add(expenseAmountField);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void addExpense() {
        String name = expenseNameField.getText();
        String amountText = expenseAmountField.getText();
        double amount = Double.parseDouble(amountText);

        Expense expense = new Expense(name, amount);
        expenses.add(expense);

        expenseListModel.addElement(name + " - $" + amount);

        expenseNameField.setText("");
        expenseAmountField.setText("");
    }

    private class Expense {
        private String name;
        private double amount;

        public Expense(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpenseTracker::new);
    }
}
