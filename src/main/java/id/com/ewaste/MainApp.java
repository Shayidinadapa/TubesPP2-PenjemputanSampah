package id.com.ewaste;

public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new id.com.ewaste.view.MainFrame().setVisible(true);
        });
    }
}