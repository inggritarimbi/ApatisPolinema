package apatis.com.apatispolinema.models;

/**
 * Created by ASUS on 18/01/2018.
 */

public class Pasien_Tetap {
    String idPasienTetap;
    String nama_pasien;
    String alamat;
    String pekerjaan;
    String umur;
    String poli;
    String tanggal;

    public Pasien_Tetap() {
    }

    public Pasien_Tetap(String idPasienTetap, String nama_pasien, String alamat, String pekerjaan, String umur, String poli, String tanggal) {
        this.idPasienTetap = idPasienTetap;
        this.nama_pasien = nama_pasien;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.umur = umur;
        this.poli = poli;
        this.tanggal = tanggal;
    }

    public String getIdPasienTetap() {
        return idPasienTetap;
    }

    public void setIdPasienTetap(String idPasienTetap) {
        this.idPasienTetap = idPasienTetap;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
