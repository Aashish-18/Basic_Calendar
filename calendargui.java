import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;

public class calendargui {
    public static void main(String[] args) {
        new cal();
    }
}

class cal extends JFrame {
    JTextField yearField, monthField;
    JButton showButton;
    JTable calendarTable;
    DefaultTableModel model;

    public cal() {
        setTitle("Calendar");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 400);

        // Input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(Color.PINK); // set background color
        yearField = new JTextField(4);
        monthField = new JTextField(2);
        showButton = new JButton("Show");
        showButton.setForeground(Color. BLACK); // set foreground color
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Month:"));
        inputPanel.add(monthField);
        inputPanel.add(showButton);
        add(inputPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"});
        calendarTable = new JTable(model);
        calendarTable.setRowHeight(30);



        JScrollPane scrollPane = new JScrollPane(calendarTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button listener
        showButton.addActionListener(ae -> {
            // Clear table
            model.setRowCount(0);

            // Get input values
            int year = Integer.parseInt(yearField.getText());
            int month = Integer.parseInt(monthField.getText());

            // Create LocalDate object and get days in month
            LocalDate date = LocalDate.of(year, month, 1);
            int daysInMonth = date.lengthOfMonth();

            // Add rows to table
            Object[] row = new Object[7];
            int dayOfWeek = date.getDayOfWeek().getValue();
            for (int i = 1; i < dayOfWeek; i++) {
                row[i - 1] = "";
            }
            int day = 1;
            for (int i = dayOfWeek - 1; i < 7; i++) {
                row[i] = day;
                day++;
            }
            model.addRow(row);
            while (day <= daysInMonth) {
                row = new Object[7];
                for (int i = 0; i < 7; i++) {
                    if (day <= daysInMonth) {
                        row[i] = day;
                        day++;
                    } else {
                        row[i] = "";
                    }
                }
                model.addRow(row);
            }
        });

    }
}

