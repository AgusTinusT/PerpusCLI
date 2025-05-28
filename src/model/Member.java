package model;

public class Member {
    private String idMember;
    private String nama;
    private String alamat;
    private String noTelp;

    public Member(String idMember, String nama, String alamat, String noTelp) {
        this.idMember = idMember;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
