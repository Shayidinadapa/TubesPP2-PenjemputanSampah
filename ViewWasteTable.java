import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWasteTable {
    public static void showTable(String[] args) {
        JFrame frame = new JFrame("View Waste");
        frame.setSize(600, 400);

        String[] columnNames = {"ID", "Category", "Description", "Waktu"};
        Object[][] data = {
            {"1", "Sampah Organik", "Daun kering", "08:00"},
            {"2", "Sampah Anorganik", "Botol plastik", "10:00"},
            {"3", "Sampah B3", "Baterai bekas", "12:00"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton isiFormButton = new JButton("Isi Form");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Action for Isi Form Button
        isiFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("Add Waste");
                addFrame.setSize(400, 300);
                addFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel jenisLabel = new JLabel("Jenis Penjemputan:");
                JLabel alamatLabel = new JLabel("Alamat:");
                JLabel categoryLabel = new JLabel("Category:");
                JLabel waktuLabel = new JLabel("Waktu Penjemputan:");

                JTextField jenisField = new JTextField();
                JTextField alamatField = new JTextField();
                String[] categories = {"Sampah Organik", "Sampah Anorganik", "Sampah B3"};
                JComboBox<String> categoryComboBox = new JComboBox<>(categories);
                JTextField waktuField = new JTextField();

                JButton saveButton = new JButton("Submit");

                // Layout for ID
                gbc.gridx = 0;
                gbc.gridy = 0;
                addFrame.add(jenisLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                addFrame.add(jenisField, gbc);

                // Layout for Category
                gbc.gridx = 0;
                gbc.gridy = 1;
                addFrame.add(alamatLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                addFrame.add(alamatField, gbc);

                // Layout for Description
                gbc.gridx = 0;
                gbc.gridy = 2;
                addFrame.add(categoryLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                addFrame.add(categoryComboBox, gbc);

                // Layout for Waktu Penjemputan
                gbc.gridx = 0;
                gbc.gridy = 3;
                addFrame.add(waktuLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                addFrame.add(waktuField, gbc);

                // Layout for Save Button
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                addFrame.add(saveButton, gbc);

                // Save Button Action
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String jenis = jenisField.getText();
                        String alamat = alamatField.getText();
                        String category = (String) categoryComboBox.getSelectedItem();
                        String waktu = waktuField.getText();

                        if (jenis.isEmpty() || alamat.isEmpty() || waktu.isEmpty()) {
                            JOptionPane.showMessageDialog(addFrame, "All fields must be filled out.");
                        } else {
                            model.addRow(new Object[]{model.getRowCount() + 1, jenis, alamat, category, waktu});
                            JOptionPane.showMessageDialog(addFrame, "Data added successfully.");
                            addFrame.dispose();
                        }
                    }
                });

                addFrame.setVisible(true);
            }
        });


                // Action for Edit Button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a row to edit.");
                    return;
                }

                // Get data from selected row
                String id = table.getValueAt(selectedRow, 0).toString();
                String category = table.getValueAt(selectedRow, 1).toString();
                String description = table.getValueAt(selectedRow, 2).toString();
                String waktu = table.getValueAt(selectedRow, 3).toString();

                // Create Edit Form
                JFrame editFrame = new JFrame("Edit Waste");
                editFrame.setSize(400, 300);
                editFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel idLabel = new JLabel("ID:");
                JLabel categoryLabel = new JLabel("Category:");
                JLabel descriptionLabel = new JLabel("Description:");
                JLabel waktuLabel = new JLabel("Waktu:");

                JTextField idField = new JTextField(id);
                String[] categories = {"Sampah Organik", "Sampah Anorganik", "Sampah B3"};
                JComboBox<String> categoryComboBox = new JComboBox<>(categories);
                categoryComboBox.setSelectedItem(category);
                JTextField descriptionField = new JTextField(description);
                JTextField waktuField = new JTextField(waktu);

                JButton saveButton = new JButton("Save");

                // Layout for ID
                gbc.gridx = 0;
                gbc.gridy = 0;
                editFrame.add(idLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                editFrame.add(idField, gbc);

                // Layout for Category
                gbc.gridx = 0;
                gbc.gridy = 1;
                editFrame.add(categoryLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                editFrame.add(categoryComboBox, gbc);

                // Layout for Description
                gbc.gridx = 0;
                gbc.gridy = 2;
                editFrame.add(descriptionLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                editFrame.add(descriptionField, gbc);

                // Layout untuk Waktu
                gbc.gridx = 0;
                gbc.gridy = 3;
                editFrame.add(waktuLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                editFrame.add(waktuField, gbc);

                // Layout for Save Button
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                editFrame.add(saveButton, gbc);

                // Save Button Action
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newId = idField.getText();
                        String newCategory = (String) categoryComboBox.getSelectedItem();
                        String newDescription = descriptionField.getText();
                        String newWaktu = waktuField.getText();

                        if (newId.isEmpty() || newDescription.isEmpty()) {
                            JOptionPane.showMessageDialog(editFrame, "All fields must be filled out.");
                        } else {
                            // Update table data
                            table.setValueAt(newId, selectedRow, 0);
                            table.setValueAt(newCategory, selectedRow, 1);
                            table.setValueAt(newDescription, selectedRow, 2);
                            table.setValueAt(newWaktu, selectedRow, 3);

                            JOptionPane.showMessageDialog(editFrame, "Data updated successfully.");
                            editFrame.dispose();
                        }
                    }
                });

                editFrame.setVisible(true);
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
        buttonPanel.add(isiFormButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
