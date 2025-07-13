package com.example.rentalin.id.model;

public class Pelanggan {
    private int id;
    private String nama;
    private String nik;

    public Pelanggan(String nama, String nik) {
        this.nama = nama;
        this.nik = nik;
    }

    public Pelanggan(int id, String nama, String nik) {
        this.id = id;
        this.nama = nama;
        this.nik = nik;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama + " (ID: " + id + ")";
    }

    public String getNIK() {
        return nik;
    }
    public void setNIK(String nik) {
        this.nik = nik;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNik() {
        return nik;
    }
}
