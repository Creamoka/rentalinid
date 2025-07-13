package com.example.rentalin.id.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.example.rentalin.id.model.Mobil;

public class HalamanDeskripsiMobilPanel extends JPanel {
    private MainFrame frame;
    private Mobil mobil;

    private JLabel lblJudul;
    private JLabel lblGambar;
    private JLabel lblTipe, lblBahanBakar, lblKapasitas, lblHarga;
    private JTextArea taDeskripsi;

    public HalamanDeskripsiMobilPanel(MainFrame frame, Mobil mobil) {
        this.frame = frame;
        this.mobil = mobil;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel atas
        JPanel atasPanel = new JPanel(new BorderLayout(10, 10));
        lblJudul = new JLabel();
        lblJudul.setFont(new Font("Arial", Font.BOLD, 22));

        lblGambar = new JLabel();
        lblGambar.setHorizontalAlignment(JLabel.CENTER);

        atasPanel.add(lblJudul, BorderLayout.NORTH);
        atasPanel.add(lblGambar, BorderLayout.CENTER);
        add(atasPanel, BorderLayout.NORTH);

        // --- Panel tengah
        JPanel detailPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        detailPanel.add(new JLabel("Tipe:"));         lblTipe = new JLabel();         detailPanel.add(lblTipe);
        detailPanel.add(new JLabel("Bahan Bakar:"));  lblBahanBakar = new JLabel();  detailPanel.add(lblBahanBakar);
        detailPanel.add(new JLabel("Kapasitas:"));    lblKapasitas = new JLabel();   detailPanel.add(lblKapasitas);
        detailPanel.add(new JLabel("Harga Sewa:"));   lblHarga = new JLabel();       detailPanel.add(lblHarga);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(detailPanel, BorderLayout.NORTH);

        // Deskripsi teks
        taDeskripsi = new JTextArea();
        taDeskripsi.setLineWrap(true);
        taDeskripsi.setWrapStyleWord(true);
        taDeskripsi.setEditable(false);
        taDeskripsi.setFont(new Font("Arial", Font.PLAIN, 13));
        taDeskripsi.setBackground(getBackground());
        taDeskripsi.setBorder(BorderFactory.createTitledBorder("Deskripsi"));
        centerPanel.add(taDeskripsi, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // --- Panel bawah
        JPanel bawahPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKembali = new JButton("← Kembali");
        JButton btnLanjut = new JButton("Lanjut Sewa");

        btnKembali.addActionListener(e -> frame.showPilihMobil());
        btnLanjut.addActionListener(e -> frame.showIsiBiodata(mobil));

        bawahPanel.add(btnKembali);
        bawahPanel.add(btnLanjut);
        add(bawahPanel, BorderLayout.SOUTH);

        // Initial update
        updateTampilan();
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
        updateTampilan();
    }

    private void updateTampilan() {
        if (mobil == null) return;

        lblJudul.setText(mobil.getMerk() + " - " + mobil.getTipe());

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + mobil.getMerk().toLowerCase() + ".png"));
            Image original = icon.getImage();
            int targetWidth = 300;
            int targetHeight = (int) ((double) original.getHeight(null) / original.getWidth(null) * targetWidth);
            Image scaled = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
            lblGambar.setIcon(new ImageIcon(scaled));
            lblGambar.setText("");
        } catch (Exception e) {
            lblGambar.setText("Gambar tidak tersedia");
            lblGambar.setIcon(null);
        }

        lblTipe.setText(mobil.getTipe());
        lblBahanBakar.setText(mobil.getBahanBakar());
        lblKapasitas.setText(mobil.getKapasitas() + " orang");
        lblHarga.setText("Rp" + mobil.getHargaSewa() + " / hari");

        taDeskripsi.setText(mobil.getDeskripsi() != null ? mobil.getDeskripsi() :
            "Mobil ini adalah " + mobil.getMerk() + " tipe " + mobil.getTipe() + ". Cocok untuk perjalanan keluarga atau bisnis dengan bahan bakar " + mobil.getBahanBakar() + ".");
    }
}
