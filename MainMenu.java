import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("E-Waste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(new GridLayout(8, 1, 10, 10));

        JButton addWasteButton = new JButton("Add Waste");
        JButton viewWasteButton = new JButton("View Waste");

        addWasteButton.setBackground(Color.GREEN);
        viewWasteButton.setBackground(Color.GREEN);

        addWasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoriWasteForm.showForm();    
            }
        });

        viewWasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewWasteTable.showTable(args);
            }
        });

        frame.add(addWasteButton);
        frame.add(viewWasteButton);

        frame.setVisible(true);
    }
}

