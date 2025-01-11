package id.com.ewaste.controller;

import id.com.ewaste.db.DatabaseConnection;
import id.com.ewaste.model.Kategori;
import java.sql.*;
import java.util.ArrayList;


public class KategoriController {
    
        // Mengambil semua kategori dari database
    public ArrayList<Kategori> getAllKategori() {
        ArrayList<Kategori> kategoriList = new ArrayList<>();
        String query = "SELECT * FROM kategori";  // Pastikan query ini mengambil data terbaru
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                kategoriList.add(new Kategori(rs.getInt("id"), rs.getString("nama")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoriList;
    }

    // Menambahkan kategori baru ke database
    public void addKategori(String nama) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO kategori (nama) VALUES (?)")) {
            pstmt.setString(1, nama);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Menghapus kategori dari database berdasarkan ID
    public void deleteKategori(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            // Hapus data penjemputan yang masih merujuk ke kategori ini
            String deletePenjemputanQuery = "DELETE FROM penjemputan WHERE kategori_id = ?";
            pstmt = conn.prepareStatement(deletePenjemputanQuery);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();

            // Hapus kategori dari tabel kategori
            String deleteKategoriQuery = "DELETE FROM kategori WHERE id = ?";
            pstmt = conn.prepareStatement(deleteKategoriQuery);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Kategori dengan ID " + id + " dan semua data penjemputannya berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Mengupdate kategori di database
    public void updateKategori(int id, String namaBaru) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE kategori SET nama = ? WHERE id = ?")) {
            pstmt.setString(1, namaBaru);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
