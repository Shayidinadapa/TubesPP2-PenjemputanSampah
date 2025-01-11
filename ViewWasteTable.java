import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWasteTable {
    public static void showTable() {
        JFrame frame = new JFrame("View Waste");
        frame.setSize(600, 400);

        String[] columnNames = {"ID", "Category", "Description"};
        Object[][] data = {
            {"1", "Sampah Organik", "Daun kering"},
            {"2", "Sampah Anorganik", "Botol plastik"},
            {"3", "Sampah B3", "Baterai bekas"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

// Action for Edit Button
editButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) table.getValueAt(selectedRow, 0);
            String category = (String) table.getValueAt(selectedRow, 1);
            String description = (String) table.getValueAt(selectedRow, 2);

            // Open Edit Form
            JFrame editFrame = new JFrame("Edit Waste");
            editFrame.setSize(300, 200);
            editFrame.setLayout(new GridLayout(3, 2, 10, 10));

            JLabel idLabel = new JLabel("ID:");
            JLabel categoryLabel = new JLabel("Category:");
            JLabel descriptionLabel = new JLabel("Description:");

            JTextField idField = new JTextField(id);
            idField.setEditable(false); // ID is non-editable
            String[] categories = {"Sampah Organik", "Sampah Anorganik", "Sampah B3"};
            JComboBox<String> categoryComboBox = new JComboBox<>(categories);
            categoryComboBox.setSelectedItem(category);
            JTextField descriptionField = new JTextField(description);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newCategory = (String) categoryComboBox.getSelectedItem();
                    String newDescription = descriptionField.getText();

                    // Update table model
                    model.setValueAt(newCategory, selectedRow, 1);
                    model.setValueAt(newDescription, selectedRow, 2);

                    JOptionPane.showMessageDialog(editFrame, "Data updated successfully.");
                    editFrame.dispose();
                }
            });

            editFrame.add(idLabel);
            editFrame.add(idField);
            editFrame.add(categoryLabel);
            editFrame.add(categoryComboBox);
            editFrame.add(descriptionLabel);
            editFrame.add(descriptionField);
            editFrame.add(saveButton);

            editFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to edit.");
        }
    }
});
// Action for Delete Button
deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this entry?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
        }
    }
});

JPanel buttonPanel = new JPanel();
buttonPanel.add(editButton);
buttonPanel.add(deleteButton);

frame.add(scrollPane, BorderLayout.CENTER);
frame.add(buttonPanel, BorderLayout.SOUTH);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}
