package model;

import java.time.LocalDate;

public class Peminjaman {
    private String idpeminjaman;
    private Member member;
    private Buku buku;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private StatusPeminjaman status;

    public enum StatusPeminjaman {
        DIPINJAM, DIKEMBALIKAN, TERLAMBAT
    }

    public Peminjaman(String idpeminjaman, Member member, Buku buku, LocalDate tanggalPinjam, LocalDate tanggalKembali, StatusPeminjaman status) {
        this.idpeminjaman = idpeminjaman;
        this.member = member;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    public String getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(String idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public StatusPeminjaman getStatus() {
        return status;
    }

    public void setStatus(StatusPeminjaman status) {
        this.status = status;
    }
}
