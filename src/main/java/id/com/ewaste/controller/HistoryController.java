package id.com.ewaste.controller;


import id.com.ewaste.db.DatabaseConnection;
import id.com.ewaste.model.History;
import java.sql.*;
import java.util.ArrayList;


public class HistoryController {
    // Mendapatkan semua history
    public ArrayList<History> getHistory() {
        ArrayList<History> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM history")) {
            while (rs.next()) {
                list.add(new History(rs.getInt("id"), rs.getString("deskripsi"), rs.getDate("tanggal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Mencari history berdasarkan kata kunci
    public ArrayList<History> searchHistory(String keyword) {
        ArrayList<History> list = new ArrayList<>();
        String query = "SELECT * FROM history WHERE deskripsi LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new History(rs.getInt("id"), rs.getString("deskripsi"), rs.getDate("tanggal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Menambahkan history baru
    public void addHistory(History history) {
        String query = "INSERT INTO history (deskripsi, tanggal) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, history.getDeskripsi());
            pstmt.setDate(2, new java.sql.Date(history.getTanggal().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus history berdasarkan ID
    public void deleteHistory(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
