# 🚗 Rentalin ID — Aplikasi Penyewaan Mobil (Java + Swing)

**Rentalin ID** adalah aplikasi desktop sederhana untuk mengelola proses penyewaan mobil. Dibuat menggunakan Java dan Swing sebagai GUI, serta MySQL sebagai database penyimpanan data pelanggan dan transaksi.

---

## ✨ Fitur Utama

- 📋 Input data penyewa & metode pembayaran
- 🚘 Pilih mobil dari daftar (hardcoded)
- 🧾 Menyimpan transaksi ke database
- 📜 Menampilkan riwayat transaksi
- 🗑 Menghapus transaksi terakhir atau semua transaksi
- ♻️ Auto reset ID jika semua data dihapus

---

## ⚙️ Persiapan & Instalasi

### 1. Buat Database MySQL

```sql
CREATE DATABASE rentalin;

### 2. Pastikan pengaturan database di DatabaseHandler.java sesuai:

private static final String DB_URL = "jdbc:mysql://localhost:3306/rentalin";
private static final String USER = "root";
private static final String PASS = "";

### 3. Struktur tabel dibuat otomatis saat pertama kali program dijalankan:

- Tabel pelanggan
- id (PK), nama, nik

- Tabel transaksi
- id (PK), pelanggan_id (FK), mobil, lama_sewa, total

### 3. Menjalankan Aplikasi

1. Buka project ini di IDE favorit kamu (NetBeans, IntelliJ, VS Code, dsb).
2. Jalankan App.java.
3. GUI akan terbuka dan kamu bisa langsung menggunakannya.

---

## 💡 Catatan Teknis

- Data mobil tidak berasal dari database (masih hardcoded di Java).
- Transaksi dan pelanggan disimpan ke database MySQL.
- Jika semua transaksi dihapus, maka:
    - Data pelanggan ikut dihapus.
    - ID akan di-reset (AUTO_INCREMENT = 1 kembali).
- Riwayat transaksi ditarik langsung dari database setiap kali ditampilkan (bukan dari memori).