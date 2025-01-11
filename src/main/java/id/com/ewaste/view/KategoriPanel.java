package id.com.ewaste.view;
import id.com.ewaste.controller.KategoriController;
import id.com.ewaste.model.Kategori;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class KategoriPanel extends JPanel {
    private JTextField namaField, searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private KategoriController controller;

    public KategoriPanel() {
        controller = new KategoriController();
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Nama Kategori:"));
        namaField = new JTextField();
        inputPanel.add(namaField);
        JButton addButton = new JButton("Tambah");
        inputPanel.add(addButton);
        JButton deleteButton = new JButton("Hapus");
        inputPanel.add(deleteButton);
        JButton editButton = new JButton("Edit");  // Tombol Edit
        inputPanel.add(editButton);  // Menambahkan tombol Edit
        add(inputPanel, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Cari");
        searchPanel.add(new JLabel("Cari Kategori:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadKategori("");  // Memuat kategori pertama kali

        // Event listeners
        addButton.addActionListener(e -> addKategori());
        deleteButton.addActionListener(e -> deleteKategori());
        searchButton.addActionListener(e -> loadKategori(searchField.getText().trim()));
        editButton.addActionListener(e -> editKategori());  // Action untuk Edit kategori
    }

    private void loadKategori(String keyword) {
        ArrayList<Kategori> list = controller.getAllKategori();
        tableModel.setRowCount(0);  // Clear tabel
        for (Kategori kategori : list) {
            if (kategori.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                tableModel.addRow(new Object[]{kategori.getId(), kategori.getNama()});
            }
        }
    }

    private void addKategori() {
        String nama = namaField.getText();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }
        controller.addKategori(nama);
        loadKategori("");  // Refresh data kategori
    }

    private void deleteKategori() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kategori yang akan dihapus!");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);

        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus kategori ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteKategori(id);
            loadKategori("");  // Refresh data kategori
        }
    }

    private void editKategori() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kategori yang akan diedit!");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String namaBaru = JOptionPane.showInputDialog(this, "Masukkan nama kategori baru:", tableModel.getValueAt(selectedRow, 1).toString());
        
        if (namaBaru != null && !namaBaru.isEmpty()) {
            controller.updateKategori(id, namaBaru);
            loadKategori("");  // Refresh data kategori
        }
    }
}
