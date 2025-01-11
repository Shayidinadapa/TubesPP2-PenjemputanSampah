package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFormTable {
    public static void main(String[] args) {
        JFrame frame = new JFrame("View Waste");
        frame.setSize(600, 400);

        String[] columnNames = {"Jenis Penjemputan", "Alamat", "Category", "Waktu Penjemputan"};
        Object[][] data = {
            {"Reguler", "Jl. Sudirman No. 1", "Sampah Organik", "08:00"},
            {"Express", "Jl. Thamrin No. 2", "Sampah Anorganik", "10:00"},
            {"Reguler", "Jl. Merdeka No. 3", "Sampah B3", "12:00"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton isiFormButton = new JButton("Isi Form");

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

                JButton submitButton = new JButton("Submit");

                // Layout for Jenis Penjemputan
                gbc.gridx = 0;
                gbc.gridy = 0;
                addFrame.add(jenisLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                addFrame.add(jenisField, gbc);

                // Layout for Alamat
                gbc.gridx = 0;
                gbc.gridy = 1;
                addFrame.add(alamatLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                addFrame.add(alamatField, gbc);

                // Layout for Category
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

                // Layout for Submit Button
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                addFrame.add(submitButton, gbc);

                // Submit Button Action
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String jenis = jenisField.getText();
                        String alamat = alamatField.getText();
                        String category = (String) categoryComboBox.getSelectedItem();
                        String waktu = waktuField.getText();

                        if (jenis.isEmpty() || alamat.isEmpty() || waktu.isEmpty()) {
                            JOptionPane.showMessageDialog(addFrame, "All fields must be filled out.");
                        } else {
                            model.addRow(new Object[]{jenis, alamat, category, waktu});
                            JOptionPane.showMessageDialog(addFrame, "Data added successfully.");
                            addFrame.dispose();
                        }
                    }
                });

                addFrame.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(isiFormButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

