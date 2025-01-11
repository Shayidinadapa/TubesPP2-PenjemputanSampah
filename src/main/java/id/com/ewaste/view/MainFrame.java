package id.com.ewaste.view;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Aplikasi Penjemputan Sampah");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Kategori", new KategoriPanel());
        tabbedPane.add("Penjemputan", new PenjemputanPanel());
        tabbedPane.add("History", new HistoryPanel());
        add(tabbedPane, BorderLayout.CENTER);
    }
}