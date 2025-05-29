import model.Buku;
import model.Member;
import service.Perpustakaan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    private static Perpustakaan perpustakaan = new Perpustakaan();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Data dummy untuk testing
        inisialisasiData();

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = getInputInteger("Masukkan pilihan Anda: ");

            switch (pilihan) {
                case 1:
                    menuManajemenBuku();
                    break;
                case 2:
                    menuManajemenMember();
                    break;
                case 3:
                    menuManajemenPeminjaman();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
            if (pilihan != 0) {
                System.out.println("\nTekan Enter untuk kembali ke menu utama...");
                scanner.nextLine(); // Konsumsi newline sisa dari nextInt/nextDouble
                scanner.nextLine(); // Tunggu user tekan enter
            }
        } while (pilihan != 0);

        scanner.close();
    }

    private static void inisialisasiData() {
        perpustakaan.tambahBuku(new Buku("BK001", "Laskar Pelangi", "Andrea Hirata", "4324", "Bentang Pustaka", 2005, 5));
        perpustakaan.tambahBuku(new Buku("BK002", "Bumi Manusia", "Pramoedya Ananta Toer","4324", "Hasta Mitra", 1980, 3));
        perpustakaan.tambahBuku(new Buku("BK003", "Cantik Itu Luka", "Eka Kurniawan", "4324", "Gramedia", 2002, 4));

        perpustakaan.tambahMember(new Member("MB001", "Alice Smith", "Jl. Merdeka No. 10", "081234567890"));
        perpustakaan.tambahMember(new Member("MB002", "Bob Johnson", "Jl. Sudirman No. 25", "087654321098"));
        System.out.println("\nData awal telah diinisialisasi.\n");
    }


    private static void tampilkanMenu() {
        System.out.println("\n===== Sistem Perpustakaan =====");
        System.out.println("1. Manajemen Buku");
        System.out.println("2. Manajemen Member");
        System.out.println("3. Manajemen Peminjaman");
        System.out.println("0. Keluar");
        System.out.println("===============================");
    }

    private static void menuManajemenBuku() {
        int pilihan;
        do {
            System.out.println("\n--- Manajemen Buku ---");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Lihat Semua Buku");
            System.out.println("3. Cari Buku (berdasarkan ID)");
            System.out.println("0. Kembali ke Menu Utama");
            pilihan = getInputInteger("Pilihan: ");

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    perpustakaan.tampilkanSemuaBuku();
                    break;
                case 3:
                    cariBuku();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            if (pilihan != 0) {
                System.out.println("\nTekan Enter untuk kembali ke menu buku...");
                scanner.nextLine();
            }
        } while (pilihan != 0);
    }

    private static void tambahBuku() {
        System.out.println("\n--- Tambah Buku Baru ---");
        System.out.print("ID Buku (misal: BK004): ");
        String id = scanner.nextLine();
        System.out.print("Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Penulis: ");
        String isbn = scanner.nextLine();
        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = scanner.nextLine();
        int tahun = getInputInteger("Tahun Terbit: ");
        int stok = getInputInteger("Jumlah Stok: ");
        perpustakaan.tambahBuku(new Buku(id, judul, penulis, isbn, penerbit, tahun, stok));
    }

    private static void cariBuku() {
        System.out.print("Masukkan ID Buku yang dicari: ");
        String idBuku = scanner.nextLine();
        perpustakaan.findBukuById(idBuku)
                .ifPresentOrElse(
                        buku -> {
                            System.out.println("--- Detail Buku ---");
                            System.out.println(buku);
                        },
                        () -> System.out.println("Buku dengan ID " + idBuku + " tidak ditemukan.")
                );
    }


    private static void menuManajemenMember() {
        int pilihan;
        do {
            System.out.println("\n--- Manajemen Member ---");
            System.out.println("1. Tambah Member Baru");
            System.out.println("2. Lihat Semua Member");
            System.out.println("3. Cari Member (berdasarkan ID)");
            System.out.println("0. Kembali ke Menu Utama");
            pilihan = getInputInteger("Pilihan: ");

            switch (pilihan) {
                case 1:
                    tambahMember();
                    break;
                case 2:
                    perpustakaan.tampilkanSemuaMember();
                    break;
                case 3:
                    cariMember();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            if (pilihan != 0) {
                System.out.println("\nTekan Enter untuk kembali ke menu member...");
                scanner.nextLine();
            }
        } while (pilihan != 0);
    }

    private static void tambahMember() {
        System.out.println("\n--- Tambah Member Baru ---");
        System.out.print("ID Member (misal: MB003): ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Nomor Telepon: ");
        String noTelp = scanner.nextLine();
        perpustakaan.tambahMember(new Member(id, nama, alamat, noTelp));
    }

    private static void cariMember() {
        System.out.print("Masukkan ID Member yang dicari: ");
        String idMember = scanner.nextLine();
        perpustakaan.findMemberById(idMember)
                .ifPresentOrElse(
                        member -> {
                            System.out.println("--- Detail Member ---");
                            System.out.println(member);
                        },
                        () -> System.out.println("Member dengan ID " + idMember + " tidak ditemukan.")
                );
    }

    private static void menuManajemenPeminjaman() {
        int pilihan;
        do {
            System.out.println("\n--- Manajemen Peminjaman ---");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Kembalikan Buku");
            System.out.println("3. Lihat Peminjaman Aktif");
            System.out.println("4. Lihat Riwayat Peminjaman (Semua)");
            System.out.println("0. Kembali ke Menu Utama");
            pilihan = getInputInteger("Pilihan: ");

            switch (pilihan) {
                case 1:
                    prosesPinjamBuku();
                    break;
                case 2:
                    prosesKembalikanBuku();
                    break;
                case 3:
                    perpustakaan.tampilkanSemuaPeminjaman(true); // true = hanya aktif
                    break;
                case 4:
                    perpustakaan.tampilkanSemuaPeminjaman(false); // false = semua riwayat
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            if (pilihan != 0) {
                System.out.println("\nTekan Enter untuk kembali ke menu peminjaman...");
                scanner.nextLine();
            }
        } while (pilihan != 0);
    }

    private static void prosesPinjamBuku() {
        System.out.println("\n--- Pinjam Buku ---");
        System.out.print("Masukkan ID Member: ");
        String idMember = scanner.nextLine();
        System.out.print("Masukkan ID Buku: ");
        String idBuku = scanner.nextLine();
        perpustakaan.pinjamBuku(idMember, idBuku);
    }

    private static void prosesKembalikanBuku() {
        System.out.println("\n--- Kembalikan Buku ---");
        System.out.print("Masukkan ID Peminjaman: ");
        String idPeminjaman = scanner.nextLine();
        perpustakaan.kembalikanBuku(idPeminjaman);
    }

    // Helper untuk input integer yang aman
    private static int getInputInteger(String prompt) {
        int input = -1; // Default value jika ada error
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine(); // Konsumsi input yang salah
            }
        }
    }
}