package id.com.ewaste.view;

import id.com.ewaste.controller.KategoriController;
import id.com.ewaste.controller.PenjemputanController;
import id.com.ewaste.model.Kategori;
import id.com.ewaste.model.Penjemputan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class PenjemputanPanel extends JPanel {
    private JComboBox<String> kategoriComboBox;
    private JTextField deskripsiField, beratField, alamatField;
    private JButton submitButton;
    private KategoriController kategoriController;
    private PenjemputanController penjemputanController;
    private ArrayList<Kategori> kategoriList;

    public PenjemputanPanel() {
        kategoriController = new KategoriController();
        penjemputanController = new PenjemputanController();

        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form fields
        add(new JLabel("Kategori Sampah:"));
        kategoriComboBox = new JComboBox<>();
        loadKategori();  // Memastikan kategori dimuat pertama kali
        add(kategoriComboBox);

        add(new JLabel("Deskripsi Sampah:"));
        deskripsiField = new JTextField();
        add(deskripsiField);

        add(new JLabel("Berat Sampah (Kg):"));
        beratField = new JTextField();
        add(beratField);

        add(new JLabel("Alamat:"));
        alamatField = new JTextField();
        add(alamatField);

        // Submit button
        submitButton = new JButton("Request Penjemputan");
        submitButton.addActionListener(e -> requestPenjemputan());
        add(submitButton);

        // Empty space for alignment
        add(new JLabel());
    }

    // Memuat kategori terbaru
    private void loadKategori() {
        kategoriList = kategoriController.getAllKategori();
        kategoriComboBox.removeAllItems();  // Menghapus kategori lama
        for (Kategori kategori : kategoriList) {
            kategoriComboBox.addItem(kategori.getNama());  // Menambahkan kategori terbaru
        }
    }

    // Request penjemputan
    private void requestPenjemputan() {
        int selectedIndex = kategoriComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kategori sampah!");
            return;
        }

        String deskripsi = deskripsiField.getText().trim();
        String beratStr = beratField.getText().trim();
        String alamat = alamatField.getText().trim();

        if (deskripsi.isEmpty() || beratStr.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        try {
            int berat = Integer.parseInt(beratStr);
            if (berat <= 0) {
                JOptionPane.showMessageDialog(this, "Berat harus lebih dari 0!");
                return;
            }

            Kategori selectedKategori = kategoriList.get(selectedIndex);
            Penjemputan penjemputan = new Penjemputan(0, selectedKategori.getId(), berat, alamat, new Date());

            // Kirim permintaan penjemputan
            penjemputanController.requestPenjemputan(penjemputan);

            // Tampilkan konfirmasi
            JOptionPane.showMessageDialog(this, "Permintaan penjemputan berhasil dikirim!");

            // Reset form dan muat ulang kategori
            clearForm();
            loadKategori();  // Memuat kategori terbaru
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Berat harus berupa angka!");
        }
    }

    // Mengosongkan form input
    private void clearForm() {
        deskripsiField.setText("");
        beratField.setText("");
        alamatField.setText("");
        kategoriComboBox.setSelectedIndex(-1);
    }
}
