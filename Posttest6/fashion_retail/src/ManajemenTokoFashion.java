import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

interface Diskonable {
    double hitungDiskonRupiah(double nominalDiskon);
    double hitungDiskonPersen(int persenDiskon);
}

abstract class Produk {
    private String namaProduk;
    private final String kategori;
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

    public void setHarga(double harga, double diskon) {
        this.harga = harga - diskon;
    }

    public void setHarga(double harga, int persenDiskon) {
        this.harga = harga - (harga * persenDiskon / 100);
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract void tampilkanInfo();
}

class Pakaian extends Produk implements Diskonable {
    private String ukuran;
    private String bahan;

    public Pakaian(String namaProduk, double harga, int stok, String ukuran, String bahan) {
        super(namaProduk, "Pakaian", harga, stok);
        this.ukuran = ukuran;
        this.bahan = bahan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama Produk  : " + getNamaProduk());
        System.out.println("Kategori     : " + getKategori());
        System.out.println("Harga        : Rp" + getHarga());
        System.out.println("Stok         : " + getStok() + " pcs");
        System.out.println("Ukuran       : " + ukuran);
        System.out.println("Bahan        : " + bahan);
        System.out.println("----------------------------------");
    }

    @Override
    public double hitungDiskonRupiah(double nominalDiskon) {
        return getHarga() - nominalDiskon;
    }

    @Override
    public double hitungDiskonPersen(int persenDiskon) {
        return getHarga() - (getHarga() * persenDiskon / 100);
    }
}

// Class Aksesoris implementasi interface Diskonable
class Aksesoris extends Produk implements Diskonable {
    private String jenis;
    private String merk;

    public Aksesoris(String namaProduk, double harga, int stok, String jenis, String merk) {
        super(namaProduk, "Aksesoris", harga, stok);
        this.jenis = jenis;
        this.merk = merk;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama Produk  : " + getNamaProduk());
        System.out.println("Kategori     : " + getKategori());
        System.out.println("Harga        : Rp" + getHarga());
        System.out.println("Stok         : " + getStok() + " pcs");
        System.out.println("Jenis        : " + jenis);
        System.out.println("Merk         : " + merk);
        System.out.println("----------------------------------");
    }

    @Override
    public double hitungDiskonRupiah(double nominalDiskon) {
        return getHarga() - nominalDiskon;
    }

    @Override
    public double hitungDiskonPersen(int persenDiskon) {
        return getHarga() - (getHarga() * persenDiskon / 100);
    }
}

// Main class dengan static dan error handling
public final class ManajemenTokoFashion {
    static ArrayList<Produk> daftarProduk = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int totalProduk = 0; 

    public static void getTotalProduk() {
        System.out.println("Total produk saat ini: " + totalProduk);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        int pilihan = 0;
        do {
            System.out.println("====== Sistem Manajemen Toko Fashion Retail ======");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Tampilkan Semua Produk");
            System.out.println("3. Update Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Terapkan Diskon ke Produk Pertama");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");

            try {
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
                        terapkanDiskon();
                        break;
                    case 6:
                        System.out.println("Terima kasih telah menggunakan sistem.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka! Silakan coba lagi.");
                input.nextLine();
            }
        } while (pilihan != 6);
    }

    static void tambahProduk() {
        clearScreen();
        try {
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
                return;
            }

            totalProduk++;
            System.out.println("Produk berhasil ditambahkan!");
            getTotalProduk();
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Gagal menambahkan produk.");
            input.nextLine();
        }
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
                try {
                    System.out.print("Masukkan harga baru: ");
                    p.setHarga(input.nextDouble());
                    System.out.print("Masukkan stok baru: ");
                    p.setStok(input.nextInt());
                    input.nextLine();
                    System.out.println("Produk berhasil diperbarui!");
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid.");
                    input.nextLine();
                }
                return;
            }
        }
        System.out.println("Produk tidak ditemukan.");
    }

    static void hapusProduk() {
        clearScreen();
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String nama = input.nextLine();
        int before = daftarProduk.size();
        daftarProduk.removeIf(p -> p.getNamaProduk().equalsIgnoreCase(nama));
        int after = daftarProduk.size();

        if (after < before) {
            totalProduk--;
            System.out.println("Produk berhasil dihapus!");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
        getTotalProduk();
    }

    static void terapkanDiskon() {
        clearScreen();
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk untuk didiskon.");
            return;
        }

        Produk produk = daftarProduk.get(0);
        double hargaAwal = produk.getHarga();

        System.out.println("Produk sebelum diskon:");
        produk.tampilkanInfo();

        if (produk instanceof Diskonable diskonProduk) {
            double hargaDiskonRp = diskonProduk.hitungDiskonRupiah(10000);
            double hargaDiskonPersen = diskonProduk.hitungDiskonPersen(10);

            System.out.println("Setelah diskon Rp10.000: Rp" + hargaDiskonRp);
            System.out.println("Setelah diskon 10%    : Rp" + hargaDiskonPersen);
        }

        produk.setHarga(hargaAwal); 
    }
}
