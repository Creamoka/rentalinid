package com.example.rentalin.id.service;

import java.util.ArrayList;
import java.util.List;

import com.example.rentalin.id.database.DatabaseHandler;
import com.example.rentalin.id.model.Mobil;
import com.example.rentalin.id.model.Pelanggan;
import com.example.rentalin.id.model.Transaksi;

public class SistemPenyewaan {
    private List<Mobil> daftarMobil;
    private List<Transaksi> daftarTransaksi;

    public SistemPenyewaan() {
        daftarMobil = new ArrayList<>();
        daftarTransaksi = new ArrayList<>();
        initMobil();
        DatabaseHandler.initDatabase();
    }

    private void initMobil() {
        daftarMobil.add(new Mobil("D 1234 AB", "Nissan", "Sport", "Bensin", 2, 250000));  

        daftarMobil.add(new Mobil("D 5678 CD", "Rolls", "Sedan", "Diesel", 4, 300000));  

        daftarMobil.add(new Mobil("D 8888 EF", "Koenigsegg", "Coupe", "Bensin", 2, 275000));  

        daftarMobil.add(new Mobil("D 9999 GH", "All New Rush", "SUV", "Solar", 6, 380000));  

        daftarMobil.add(new Mobil("D 1010 IJ", "CRV - R", "SUV", "Solar", 6, 420000));  

        daftarMobil.add(new Mobil("D 1111 KL", "MG ZX Exclusice", "SUV", "Solar", 6, 510000));  

        daftarMobil.add(new Mobil("D 1212 MN", "New MG ZS", "SUV", "Solar", 6, 480000));  

        daftarMobil.add(new Mobil("D 1313 OP", "All New Terios", "SUV", "Solar", 6, 320000));  

    }

    public List<Mobil> getDaftarMobil() {
        return daftarMobil;
    }

    public void tambahTransaksi(Transaksi t) {
        daftarTransaksi.add(t);
        DatabaseHandler.insertTransaksi(t);
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public Transaksi sewaMobil(String nama, String nik, Mobil mobil, int lama, String metode) {
        Pelanggan pelanggan = new Pelanggan(0, nama, nik);
        int id = DatabaseHandler.insertPelanggan(pelanggan);

        if (id != -1) {
            pelanggan = new Pelanggan(id, nama, nik);
            Transaksi t = new Transaksi(pelanggan, mobil, lama, metode);
            tambahTransaksi(t);
            return t;
        } else {
            System.out.println("Gagal simpan pelanggan.");
            return null;
        }
    }
}
