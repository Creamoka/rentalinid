package com.example.rentalin.id.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.rentalin.id.model.Mobil;
import com.example.rentalin.id.service.SistemPenyewaan;

public class MainFrame extends JFrame {
    public SistemPenyewaan sistem;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private HalamanIntroPanel halamanIntro;
    private HalamanPilihMobilPanel halamanPilih;
    private HalamanDeskripsiMobilPanel halamanDeskripsi;
    private HalamanIsiBiodataPanel halamanBiodata;
    private HalamanRiwayatPanel halamanRiwayat;

    public MainFrame() {
        super("Rentalin Mobil");
        setSize(1024, 640);
        setLocationRelativeTo(null);

        sistem = new SistemPenyewaan();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        halamanIntro = new HalamanIntroPanel(this);

        halamanPilih = new HalamanPilihMobilPanel(this);
        halamanDeskripsi = new HalamanDeskripsiMobilPanel(this, null);
        halamanBiodata = new HalamanIsiBiodataPanel(this);
        halamanRiwayat = new HalamanRiwayatPanel(this);

        mainPanel.add(halamanIntro, "intro");
        mainPanel.add(halamanPilih, "pilih");
        mainPanel.add(halamanDeskripsi, "deskripsi");
        mainPanel.add(halamanBiodata, "biodata");
        mainPanel.add(halamanRiwayat, "riwayat");

        setContentPane(mainPanel);
        cardLayout.show(mainPanel, "intro");
        setVisible(true);
    }

    public void showIntro() {
        cardLayout.show(mainPanel, "intro");
    }

    public void showPilihMobil() {
        cardLayout.show(mainPanel, "pilih");
    }

    public void showDeskripsiMobil(Mobil mobil) {
        halamanDeskripsi.setMobil(mobil);
        cardLayout.show(mainPanel, "deskripsi");
    }

    public void showIsiBiodata(Mobil mobil) {
        halamanBiodata.setMobil(mobil);
        cardLayout.show(mainPanel, "biodata");
    }

    public void showRiwayat() {
        halamanRiwayat = new HalamanRiwayatPanel(this);
        mainPanel.add(halamanRiwayat, "riwayat");
        cardLayout.show(mainPanel, "riwayat");
    }

}
