package com.example.rentalin.id.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.rentalin.id.model.Mobil;
import com.example.rentalin.id.model.Pelanggan;
import com.example.rentalin.id.model.Transaksi;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rentalin";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void initDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sqlPelanggan = "CREATE TABLE IF NOT EXISTS pelanggan (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY," +
                                  "nama VARCHAR(100) NOT NULL," +
                                  "nik VARCHAR(20) NOT NULL)";

            String sqlTransaksi = "CREATE TABLE IF NOT EXISTS transaksi (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY," +
                                  "pelanggan_id INT," +
                                  "mobil VARCHAR(50)," +
                                  "tipe VARCHAR(50)," +
                                  "lama_sewa INT," +
                                  "metode_pembayaran VARCHAR(50)," +
                                  "total INT," +
                                  "FOREIGN KEY (pelanggan_id) REFERENCES pelanggan(id))";

            stmt.execute(sqlPelanggan);
            stmt.execute(sqlTransaksi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertPelanggan(Pelanggan p) {
        try (Connection conn = connect()) {
            String sql = "INSERT INTO pelanggan(nama, nik) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getNIK());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void insertTransaksi(Transaksi t) {
        try (Connection conn = connect()) {
        String sql = "INSERT INTO transaksi(pelanggan_id, mobil, lama_sewa, metode_pembayaran, total) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, t.getPelanggan().getId());
            stmt.setString(2, t.getMobil().getMerk() + " " + t.getMobil().getTipe());
            stmt.setInt(3, t.getLamaSewa());
            stmt.setString(4, t.getMetodePembayaran());
            stmt.setInt(5, t.getTotalHarga());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaksi> ambilSemuaTransaksi() {
        List<Transaksi> list = new ArrayList<>();
        try (Connection conn = connect()) {
            String sql = "SELECT t.*, p.nama, p.nik FROM transaksi t " +
                        "JOIN pelanggan p ON t.pelanggan_id = p.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String mobilStr = rs.getString("mobil");
                String[] parts = mobilStr.split(" ", 2);
                String merk = parts.length > 0 ? parts[0] : "";
                String tipe = parts.length > 1 ? parts[1] : "";

                Pelanggan p = new Pelanggan(rs.getString("nama"), rs.getString("nik"));
                p.setId(rs.getInt("pelanggan_id"));
                Mobil m = new Mobil("", merk, tipe, "", 0, 0);
                Transaksi t = new Transaksi(
                    p,
                    m,
                    rs.getInt("lama_sewa"),
                    rs.getString("metode_pembayaran")
                );
                t.setTotalHarga(rs.getInt("total"));
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void hapusSemuaTransaksi() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM transaksi");
            stmt.executeUpdate("DELETE FROM pelanggan");

            stmt.executeUpdate("ALTER TABLE transaksi AUTO_INCREMENT = 1");
            stmt.executeUpdate("ALTER TABLE pelanggan AUTO_INCREMENT = 1");

            System.out.println("Semua data dan AUTO_INCREMENT di-reset.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void hapusTransaksiTerakhir() {
        try (Connection conn = connect()) {
            String ambil = "SELECT id, pelanggan_id FROM transaksi ORDER BY id DESC LIMIT 1";
            PreparedStatement stmt1 = conn.prepareStatement(ambil);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next()) {
                int transaksiId = rs.getInt("id");
                int pelangganId = rs.getInt("pelanggan_id");

                String hapusTransaksi = "DELETE FROM transaksi WHERE id = ?";
                PreparedStatement stmt2 = conn.prepareStatement(hapusTransaksi);
                stmt2.setInt(1, transaksiId);
                stmt2.executeUpdate();

                String hapusPelanggan = "DELETE FROM pelanggan WHERE id = ?";
                PreparedStatement stmt3 = conn.prepareStatement(hapusPelanggan);
                stmt3.setInt(1, pelangganId);
                stmt3.executeUpdate();

                System.out.println("Transaksi terakhir dan pelanggan terkait dihapus.");
            } else {
                System.out.println("Tidak ada transaksi untuk dihapus.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
