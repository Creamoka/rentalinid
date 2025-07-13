package com.example.rentalin.id.model;

public class Transaksi {
    private Pelanggan pelanggan;
    private Mobil mobil;
    private int lamaSewa;
    private String metodePembayaran;
    private int totalHarga;

    public Transaksi(Pelanggan pelanggan, Mobil mobil, int lamaSewa, String metodePembayaran) {
        this.pelanggan = pelanggan;
        this.mobil = mobil;
        this.lamaSewa = lamaSewa;
        this.metodePembayaran = metodePembayaran;
        this.totalHarga = mobil.getHargaSewa() * lamaSewa;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public String hitungTotalHarga() {
        return String.format("%,d", getTotalHarga());
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
