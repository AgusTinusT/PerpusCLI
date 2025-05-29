package model;

public class Buku {
    private String idBuku;
    private String judul;
    private String penulis;
    private String isbn;
    private String penerbit;
    private int tahunTerbit;
    private int stok;
    private int stokDipinjam;

    public Buku(String idBuku, String judul, String penulis, String isbn, String penerbit, int tahunTerbit, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.isbn = isbn;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.stok = stok;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getStokDipinjam() {
        return stokDipinjam;
    }

    public void setStokDipinjam(int stokDipinjam) {
        this.stokDipinjam = stokDipinjam;
    }

    public int getStokTersedia(){
        return this.stok - this.stokDipinjam;
    }

    public boolean pinjam() {
        if (getStokTersedia() > 0) {
            stokDipinjam++;
            return true;
        }
        return false;
    }

    public void kembalikan() {
        if (stokDipinjam > 0) {
            stokDipinjam--;
        }
    }

    @Override
    public String toString() {
        return "ID Buku     : " + idBuku + "\n" +
                "Judul       : " + judul + "\n" +
                "Penulis     : " + penulis + "\n" +
                "Penerbit    : " + penerbit + "\n" +
                "Tahun Terbit: " + tahunTerbit + "\n" +
                "Stok Total  : " + stok + "\n" +
                "Dipinjam    : " + stokDipinjam + "\n" +
                "Tersedia    : " + getStokTersedia();
    }
}
