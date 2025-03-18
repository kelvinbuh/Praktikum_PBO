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

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

public class ManajemenTokoFashion {
    static ArrayList<Produk> daftarProduk = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

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
                    clearScreen();
                    tambahProduk();
                    break;
                case 2:
                    tampilkanProduk();
                    break;
                case 3:
                    clearScreen();
                    updateProduk();
                    break;
                case 4:
                    clearScreen();
                    hapusProduk();
                    break;
                case 5:
                    clearScreen();
                    System.out.println("Terima kasih telah menggunakan sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 5);
    }

    static void tambahProduk() {
        System.out.print("Masukkan Nama Produk: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Kategori Produk: ");
        String kategori = input.nextLine();
        System.out.print("Masukkan Harga Produk: ");
        double harga = input.nextDouble();
        System.out.print("Masukkan Stok Produk: ");
        int stok = input.nextInt();
        input.nextLine(); // flush buffer

        Produk produkBaru = new Produk(nama, kategori, harga, stok);
        daftarProduk.add(produkBaru);
        System.out.println("Produk berhasil ditambahkan!\n");
    }

    static void tampilkanProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk yang ditambahkan.");
        } else {
            System.out.println("\nDaftar Produk:");
            for (int i = 0; i < daftarProduk.size(); i++) {
                System.out.println("Produk ke-" + (i + 1));
                daftarProduk.get(i).tampilkanInfo();
            }
        }
    }

    static void updateProduk() {
        tampilkanProduk();
        if (daftarProduk.isEmpty()) return;

        System.out.print("Masukkan nomor produk yang ingin diupdate: ");
        int index = input.nextInt();
        input.nextLine(); // flush buffer

        if (index <= 0 || index > daftarProduk.size()) {
            System.out.println("Nomor produk tidak valid.");
            return;
        }

        Produk produk = daftarProduk.get(index - 1);
        System.out.print("Masukkan Nama Baru (sebelumnya: " + produk.getNamaProduk() + "): ");
        String namaBaru = input.nextLine();
        System.out.print("Masukkan Kategori Baru (sebelumnya: " + produk.getKategori() + "): ");
        String kategoriBaru = input.nextLine();
        System.out.print("Masukkan Harga Baru (sebelumnya: " + produk.getHarga() + "): ");
        double hargaBaru = input.nextDouble();
        System.out.print("Masukkan Stok Baru (sebelumnya: " + produk.getStok() + "): ");
        int stokBaru = input.nextInt();
        input.nextLine(); // flush buffer

        produk.setNamaProduk(namaBaru);
        produk.setKategori(kategoriBaru);
        produk.setHarga(hargaBaru);
        produk.setStok(stokBaru);
        System.out.println("Produk berhasil diperbarui!\n");
    }

    static void hapusProduk() {
        tampilkanProduk();
        if (daftarProduk.isEmpty()) return;

        System.out.print("Masukkan nomor produk yang ingin dihapus: ");
        int index = input.nextInt();
        input.nextLine(); // flush buffer

        if (index <= 0 || index > daftarProduk.size()) {
            System.out.println("Nomor produk tidak valid.");
            return;
        }

        daftarProduk.remove(index - 1);
        System.out.println("Produk berhasil dihapus!\n");
    }

    public static void clearScreen(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033{2J}");
                System.out.flush();
            }
        } catch (Exception e){
            System.out.println("Gagal membersihkan layar");
        }
    }
}
