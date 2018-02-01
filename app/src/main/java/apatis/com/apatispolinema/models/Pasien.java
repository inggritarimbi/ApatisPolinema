package apatis.com.apatispolinema.models;

/**
 * Created by ASUS on 04/01/2018.
 */

public class Pasien {
    String idPasien;
    String email;
    String nama_pasien;
    String alamat;
    String pekerjaan;
    String umur;
    String poli;
    String nomorAntrian;

    public Pasien() {
    }

    public Pasien(String idPasien,String nama_pasien, String alamat, String pekerjaan, String umur, String poli,String nomorAntrian,String email) {
        this.idPasien = idPasien;
        this.nama_pasien = nama_pasien;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.umur = umur;
        this.poli = poli;
        this.nomorAntrian = nomorAntrian;
        this.email = email;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
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

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public void setNomorAntrian(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
