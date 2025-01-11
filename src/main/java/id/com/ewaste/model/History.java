package id.com.ewaste.model;
import java.util.Date;

public class History {
    private final int id;
    private final String deskripsi;
    private final Date tanggal;

    public History(int id, String deskripsi, Date tanggal) {
        this.id = id;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public Date getTanggal() {
        return tanggal;
    }
}
