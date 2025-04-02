import java.util.ArrayList;
import java.util.Scanner;

class Produk {
    private String namaProduk;
    private String kategori;
    private double harga;
    private int stok;

    public Produk(String namaProduk, String kategori, double harga, int stok) {
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Produk  : " + namaProduk);
        System.out.println("Kategori     : " + kategori);
        System.out.println("Harga        : Rp" + harga);
        System.out.println("Stok         : " + stok + " pcs");
        System.out.println("----------------------------------");
    }
}

class Pakaian extends Produk {
    private String ukuran;
    private String bahan;

    public Pakaian(String namaProduk, double harga, int stok, String ukuran, String bahan) {
        super(namaProduk, "Pakaian", harga, stok);
        this.ukuran = ukuran;
        this.bahan = bahan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Ukuran       : " + ukuran);
        System.out.println("Bahan        : " + bahan);
        System.out.println("----------------------------------");
    }
}

class Aksesoris extends Produk {
    private String jenis;
    private String merk;

    public Aksesoris(String namaProduk, double harga, int stok, String jenis, String merk) {
        super(namaProduk, "Aksesoris", harga, stok);
        this.jenis = jenis;
        this.merk = merk;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis        : " + jenis);
        System.out.println("Merk         : " + merk);
        System.out.println("----------------------------------");
    }
}

public class ManajemenTokoFashion {
    static ArrayList<Produk> daftarProduk = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("====== Sistem Manajemen Toko Fashion Retail ======");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Tampilkan Semua Produk");
            System.out.println("3. Update Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tambahProduk();
                    break;
                case 2:
                    tampilkanProduk();
                    break;
                case 3:
                    updateProduk();
                    break;
                case 4:
                    hapusProduk();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 5);
    }

    static void tambahProduk() {
        clearScreen();
        System.out.print("Masukkan Nama Produk: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Harga Produk: ");
        double harga = input.nextDouble();
        System.out.print("Masukkan Stok Produk: ");
        int stok = input.nextInt();
        input.nextLine();

        System.out.println("Pilih kategori produk:");
        System.out.println("1. Pakaian");
        System.out.println("2. Aksesoris");
        System.out.print("Pilihan: ");
        int kategoriPilihan = input.nextInt();
        input.nextLine();

        if (kategoriPilihan == 1) {
            System.out.print("Masukkan Ukuran: ");
            String ukuran = input.nextLine();
            System.out.print("Masukkan Bahan: ");
            String bahan = input.nextLine();
            daftarProduk.add(new Pakaian(nama, harga, stok, ukuran, bahan));
        } else if (kategoriPilihan == 2) {
            System.out.print("Masukkan Jenis Aksesoris: ");
            String jenis = input.nextLine();
            System.out.print("Masukkan Merk: ");
            String merk = input.nextLine();
            daftarProduk.add(new Aksesoris(nama, harga, stok, jenis, merk));
        } else {
            System.out.println("Kategori tidak valid, produk gagal ditambahkan.");
        }

        System.out.println("Produk berhasil ditambahkan!");
    }

    static void tampilkanProduk() {
        clearScreen();
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk yang ditambahkan.");
        } else {
            for (Produk p : daftarProduk) {
                p.tampilkanInfo();
            }
        }
    }

    static void updateProduk() {
        clearScreen();
        System.out.print("Masukkan nama produk yang ingin diupdate: ");
        String nama = input.nextLine();
        for (Produk p : daftarProduk) {
            if (p.getNamaProduk().equalsIgnoreCase(nama)) {
                System.out.print("Masukkan harga baru: ");
                p.setHarga(input.nextDouble());
                System.out.print("Masukkan stok baru: ");
                p.setStok(input.nextInt());
                input.nextLine();
                System.out.println("Produk berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Produk tidak ditemukan.");
    }

    static void hapusProduk() {
        clearScreen();
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String nama = input.nextLine();
        daftarProduk.removeIf(p -> p.getNamaProduk().equalsIgnoreCase(nama));
        System.out.println("Produk berhasil dihapus!");
    }
}
