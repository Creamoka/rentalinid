🚗 Rentalin ID - Aplikasi Penyewaan Mobil Java
Rentalin ID adalah aplikasi desktop berbasis Java + Swing yang digunakan untuk mencatat transaksi penyewaan mobil. Aplikasi ini menggunakan MySQL sebagai basis data untuk menyimpan data pelanggan dan transaksi.

✨ Fitur Utama
📋 Input Biodata Penyewa & Pilih Metode Pembayaran

🚘 Pilih Mobil dari daftar yang tersedia (data mobil bersifat hardcoded)

📊 Riwayat Transaksi disimpan ke database dan dapat dilihat kapan saja

🗑 Hapus Riwayat Terakhir atau hapus semua riwayat

🔄 ID otomatis reset saat data kosong (termasuk ID pelanggan dan transaksi)

💾 Terhubung langsung ke MySQL database

🧱 Struktur Proyek
bash
Salin
Edit
rentalinid/
├── com.example.rentalin.id.model/
│   ├── Mobil.java
│   ├── Pelanggan.java
│   └── Transaksi.java
├── com.example.rentalin.id.database/
│   └── DatabaseHandler.java
├── com.example.rentalin.id.service/
│   └── SistemPenyewaan.java
├── com.example.rentalin.id.gui/
│   ├── MainFrame.java
│   ├── HalamanPilihMobilPanel.java
│   ├── HalamanDeskripsiMobilPanel.java
│   ├── HalamanIsiBiodataPanel.java
│   └── HalamanRiwayatPanel.java
└── com.example.rentalin.id/
    └── App.java
⚙️ Pengaturan Awal
Instal MySQL dan buat database:

sql
Salin
Edit
CREATE DATABASE rentalin;
Pengaturan DatabaseHandler (sudah disiapkan default):

java
Salin
Edit
private static final String DB_URL = "jdbc:mysql://localhost:3306/rentalin";
private static final String USER = "root";
private static final String PASS = "";
Struktur tabel otomatis dibuat oleh initDatabase() saat program pertama kali dijalankan:

sql
Salin
Edit
Tabel pelanggan: id, nama, nik
Tabel transaksi: id, pelanggan_id (FK), mobil, lama_sewa, total
▶️ Cara Menjalankan
Import project ke IDE seperti IntelliJ IDEA atau NetBeans.

Pastikan koneksi database MySQL aktif dan database rentalin tersedia.

Jalankan App.java sebagai entry point:

java
Salin
Edit
public class App {
    public static void main(String[] args) {
        DatabaseHandler.initDatabase();
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
🧪 Fitur CRUD & Behavior
Tambah Transaksi:

Data pelanggan disimpan ke tabel pelanggan.

Transaksi disimpan dengan foreign key pelanggan_id.

Hapus Transaksi:

hapusTransaksiTerakhir() akan hapus satu transaksi terakhir beserta pelanggan-nya.

hapusSemuaTransaksi() akan bersihkan seluruh isi transaksi & pelanggan, dan reset auto-increment ID.

Riwayat:

Menampilkan semua transaksi dari database, bukan dari memory.

🧊 Catatan
Data mobil tidak disimpan ke database (bersifat hardcoded).

Cocok sebagai latihan pemrograman Java OOP + GUI + database (CRUD).

Jika ingin produksi: pertimbangkan input mobil dari DB juga.

