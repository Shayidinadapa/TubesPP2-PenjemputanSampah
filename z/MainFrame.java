package z;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MainFrame extends JFrame {
    public void initialize(User user) {
        //info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(user.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("Nomor Hp"));
        infoPanel.add(new JLabel(user.phone));
        infoPanel.add(new JLabel("Alamat"));
        infoPanel.add(new JLabel(user.address));

        add(infoPanel, BorderLayout.NORTH);


        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
