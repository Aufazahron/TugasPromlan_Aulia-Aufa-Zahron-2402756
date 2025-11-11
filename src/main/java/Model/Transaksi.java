package Model;

import java.time.LocalDate;

public class Transaksi {
    private double nominal;
    private String deskripsi;
    private String jenis;
    private LocalDate tanggal;

    public Transaksi(double nominal, String deskripsi, String jenis, LocalDate tanggal) {
        this.nominal = nominal;
        this.deskripsi = deskripsi;
        this.jenis = jenis;
        this.tanggal = tanggal;
    }

    public double getNominal() { return nominal; }
    public void setNominal(double nominal) { this.nominal = nominal; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
}
