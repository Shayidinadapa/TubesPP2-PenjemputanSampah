import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWasteForm {
    public static void showForm() {
        JFrame frame = new JFrame("Add Waste");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel categoryLabel = new JLabel("Category:");
        JLabel descriptionLabel = new JLabel("Description:");

        String[] categories = {"Sampah Organik", "Sampah Anorganik", "Sampah B3"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        JTextField descriptionField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = (String) categoryComboBox.getSelectedItem();
                String description = descriptionField.getText();
                // Simpan data ke database atau file di sini
                JOptionPane.showMessageDialog(frame, "Data saved:\nCategory: " + category + "\nDescription: " + description);
                frame.dispose();
            }
        });

        frame.add(categoryLabel);
        frame.add(categoryComboBox);
        frame.add(descriptionLabel);
        frame.add(descriptionField);
        frame.add(saveButton);

        frame.setVisible(true);
    }
}
