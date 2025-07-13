package com.example.rentalin.id.model;

public class Mobil {
    private String plat;
    private String merk;
    private String tipe;
    private String bahanBakar;
    private int kapasitas;
    private int hargaSewa;
    private String deskripsi;

    public Mobil(String plat, String merk, String tipe, String bahanBakar, int kapasitas, int hargaSewa) {
        this.plat = plat;
        this.merk = merk;
        this.tipe = tipe;
        this.bahanBakar = bahanBakar;
        this.kapasitas = kapasitas;
        this.hargaSewa = hargaSewa;
        this.deskripsi = deskripsi;
    }

    public String getPlat() {
        return plat;
    }

    public String getMerk() {
        return merk;
    }

    public String getTipe() {
        return tipe;
    }

    public String getBahanBakar() {
        return bahanBakar;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setBahanBakar(String bahanBakar) {
        this.bahanBakar = bahanBakar;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
