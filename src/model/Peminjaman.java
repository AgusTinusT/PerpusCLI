package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Peminjaman {
    private String idPeminjaman;
    private Member member;
    private Buku buku;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private LocalDate tanggalPengembalianAktual;
    private StatusPeminjaman status;

    public enum StatusPeminjaman {
        DIPINJAM, DIKEMBALIKAN, TERLAMBAT
    }

    public Peminjaman(String idPeminjaman, Member member, Buku buku, LocalDate tanggalPinjam, LocalDate tanggalKembali, StatusPeminjaman status) {
        this.idPeminjaman = idPeminjaman;
        this.member = member;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    
    public void setTanggalPengembalianAktual(LocalDate tanggalPengembalianAktual) {
        this.tanggalPengembalianAktual = tanggalPengembalianAktual;
    }

    public void setStatus(StatusPeminjaman status) {
        this.status = status;
    }

    public Peminjaman(String idPeminjaman, Member member, Buku buku, LocalDate tanggalPinjam) {
        this.idPeminjaman = idPeminjaman;
        this.member = member;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tglPengembalianStr = (tanggalPengembalianAktual != null) ? tanggalPengembalianAktual.format(formatter) : "Belum Dikembalikan";
        return "ID Peminjaman : " + idPeminjaman + "\n" +
                "Member        : " + member.getNama() + " (ID: " + member.getIdMember() + ")\n" +
                "Buku          : " + buku.getJudul() + " (ID: " + buku.getIdBuku() + ")\n" +
                "Tgl Pinjam    : " + tanggalPinjam.format(formatter) + "\n" +
                "Tgl Jatuh Tempo: " + tanggalKembali.format(formatter) + "\n" +
                "Tgl Kembali   : " + tglPengembalianStr + "\n" +
                "Status        : " + status;
    }

    public String getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
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

    public LocalDate getTanggalPengembalianAktual() {
        return tanggalPengembalianAktual;
    }

    public StatusPeminjaman getStatus() {
        return status;
    }

    
}
