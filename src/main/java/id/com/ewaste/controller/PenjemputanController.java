package id.com.ewaste.controller;


import id.com.ewaste.db.DatabaseConnection;
import id.com.ewaste.model.History;
import id.com.ewaste.model.Penjemputan;
import java.sql.*;
import java.util.ArrayList;

public class PenjemputanController {
    public void requestPenjemputan(Penjemputan penjemputan) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO penjemputan (kategori_id, jumlah, alamat, tanggal) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, penjemputan.getKategoriId());
            pstmt.setInt(2, penjemputan.getJumlah());
            pstmt.setString(3, penjemputan.getAlamat());
            pstmt.setDate(4, new java.sql.Date(penjemputan.getTanggal().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void addHistory(History history) {
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

    public ArrayList<Penjemputan> getPenjemputanHistory() {
        ArrayList<Penjemputan> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM penjemputan")) {
            while (rs.next()) {
                list.add(new Penjemputan(rs.getInt("id"), rs.getInt("kategori_id"), rs.getInt("jumlah"), rs.getString("alamat"), rs.getDate("tanggal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}