package id.com.ewaste.model;

import java.util.Date;
public class Penjemputan {
    private int id;
    private int kategoriId;
    private int jumlah;
    private String alamat;
    private Date tanggal;

    public Penjemputan(int id, int kategoriId, int jumlah, String alamat, Date tanggal) {
        this.id = id;
        this.kategoriId = kategoriId;
        this.jumlah = jumlah;
        this.alamat = alamat;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getAlamat() {
        return alamat;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public Object getNama() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNama'");
    }
}