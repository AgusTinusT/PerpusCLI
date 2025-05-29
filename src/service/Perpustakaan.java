package service;

import model.Buku;
import model.Member;
import model.Peminjaman;
import model.Peminjaman.StatusPeminjaman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.UUID; // Untuk ID Peminjaman unik

public class Perpustakaan {
    private List<Buku> daftarBuku;
    private List<Member> daftarMember;
    private List<Peminjaman> daftarPeminjaman;

    public Perpustakaan() {
        this.daftarBuku = new ArrayList<>();
        this.daftarMember = new ArrayList<>();
        this.daftarPeminjaman = new ArrayList<>();
    }

    // --- Manajemen Buku ---
    public void tambahBuku(Buku buku) {
        // Cek apakah ID buku sudah ada
        if (findBukuById(buku.getIdBuku()).isPresent()) {
            System.out.println("Error: Buku dengan ID " + buku.getIdBuku() + " sudah ada.");
            return;
        }
        daftarBuku.add(buku);
        System.out.println("Buku '" + buku.getJudul() + "' berhasil ditambahkan.");
    }

    public Optional<Buku> findBukuById(String idBuku) {
        return daftarBuku.stream()
                .filter(b -> b.getIdBuku().equalsIgnoreCase(idBuku))
                .findFirst();
    }

    public void tampilkanSemuaBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku terdaftar.");
            return;
        }
        System.out.println("\n--- Daftar Buku ---");
        for (Buku buku : daftarBuku) {
            System.out.println(buku);
            System.out.println("--------------------");
        }
    }

    // --- Manajemen Member ---
    public void tambahMember(Member member) {
        if (findMemberById(member.getIdMember()).isPresent()) {
            System.out.println("Error: Member dengan ID " + member.getIdMember() + " sudah ada.");
            return;
        }
        daftarMember.add(member);
        System.out.println("Member '" + member.getNama() + "' berhasil ditambahkan.");
    }

    public Optional<Member> findMemberById(String idMember) {
        return daftarMember.stream()
                .filter(m -> m.getIdMember().equalsIgnoreCase(idMember))
                .findFirst();
    }

    public void tampilkanSemuaMember() {
        if (daftarMember.isEmpty()) {
            System.out.println("Belum ada member terdaftar.");
            return;
        }
        System.out.println("\n--- Daftar Member ---");
        for (Member member : daftarMember) {
            System.out.println(member);
            System.out.println("--------------------");
        }
    }

    // --- Manajemen Peminjaman ---
    public boolean pinjamBuku(String idMember, String idBuku) {
        Optional<Member> memberOpt = findMemberById(idMember);
        Optional<Buku> bukuOpt = findBukuById(idBuku);

        if (!memberOpt.isPresent()) {
            System.out.println("Error: Member dengan ID " + idMember + " tidak ditemukan.");
            return false;
        }
        if (!bukuOpt.isPresent()) {
            System.out.println("Error: Buku dengan ID " + idBuku + " tidak ditemukan.");
            return false;
        }

        Buku buku = bukuOpt.get();
        Member member = memberOpt.get();

        if (buku.getStokTersedia() <= 0) {
            System.out.println("Error: Stok buku '" + buku.getJudul() + "' habis.");
            return false;
        }

        // Cek apakah member sudah meminjam buku ini dan belum dikembalikan
        boolean sudahPinjam = daftarPeminjaman.stream()
                .anyMatch(p -> p.getMember().equals(member) &&
                        p.getBuku().equals(buku) &&
                        p.getStatus() == Peminjaman.StatusPeminjaman.DIPINJAM);

        if (sudahPinjam) {
            System.out.println("Error: Member " + member.getNama() + " sudah meminjam buku '" + buku.getJudul() + "' dan belum dikembalikan.");
            return false;
        }


        if (buku.pinjam()) {
            String idPeminjaman = "PJ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Peminjaman peminjaman = new Peminjaman(idPeminjaman, member, buku, LocalDate.now());
            daftarPeminjaman.add(peminjaman);
            System.out.println("Buku '" + buku.getJudul() + "' berhasil dipinjam oleh '" + member.getNama() + "'.");
            System.out.println("ID Peminjaman: " + idPeminjaman);
            return true;
        } else {
            System.out.println("Gagal meminjam buku '" + buku.getJudul() + "'. Stok tidak mencukupi.");
            return false;
        }
    }

    public Optional<Peminjaman> findPeminjamanById(String idPeminjaman) {
        return daftarPeminjaman.stream()
                .filter(p -> p.getIdPeminjaman().equalsIgnoreCase(idPeminjaman) && p.getStatus() == StatusPeminjaman.DIPINJAM)
                .findFirst();
    }

    public boolean kembalikanBuku(String idPeminjaman) {
        Optional<Peminjaman> peminjamanOpt = findPeminjamanById(idPeminjaman);

        if (!peminjamanOpt.isPresent()) {
            System.out.println("Error: Peminjaman dengan ID " + idPeminjaman + " tidak ditemukan atau sudah dikembalikan.");
            return false;
        }

        Peminjaman peminjaman = peminjamanOpt.get();
        peminjaman.getBuku().kembalikan();
        peminjaman.setStatus(StatusPeminjaman.DIKEMBALIKAN);
        peminjaman.setTanggalPengembalianAktual(LocalDate.now());

        System.out.println("Buku '" + peminjaman.getBuku().getJudul() + "' berhasil dikembalikan oleh '" + peminjaman.getMember().getNama() + "'.");

        // Cek keterlambatan
        if (peminjaman.getTanggalPengembalianAktual().isAfter(peminjaman.getTanggalKembali())) {
            System.out.println("PENGEMBALIAN TERLAMBAT!");
            peminjaman.setStatus(StatusPeminjaman.TERLAMBAT); // Atau tetap DIKEMBALIKAN tapi dengan catatan
        }
        return true;
    }

    public void tampilkanSemuaPeminjaman(boolean hanyaYangAktif) {
        List<Peminjaman> listToShow = new ArrayList<>();
        if (hanyaYangAktif) {
            daftarPeminjaman.stream()
                    .filter(p -> p.getStatus() == StatusPeminjaman.DIPINJAM)
                    .forEach(listToShow::add);
            System.out.println("\n--- Daftar Peminjaman Aktif ---");
        } else {
            listToShow.addAll(daftarPeminjaman);
            System.out.println("\n--- Riwayat Peminjaman ---");
        }

        if (listToShow.isEmpty()) {
            System.out.println(hanyaYangAktif ? "Tidak ada peminjaman aktif." : "Belum ada riwayat peminjaman.");
            return;
        }

        for (Peminjaman p : listToShow) {
            System.out.println(p);
            System.out.println("--------------------");
        }
    }
}