package com.example.rentalin.id.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.example.rentalin.id.model.Mobil;

public class HalamanPilihMobilPanel extends JPanel {

    public HalamanPilihMobilPanel(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblJudul = new JLabel("Pilih Mobil", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblJudul, BorderLayout.NORTH);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20)); // 4 kolom, jarak antar elemen
        gridPanel.setMaximumSize(new Dimension(1100, Integer.MAX_VALUE));

        List<Mobil> daftarMobil = frame.sistem.getDaftarMobil();

        for (Mobil mobil : daftarMobil) {
            JPanel mobilCard = new JPanel();
            mobilCard.setLayout(new BorderLayout(5, 5));
            mobilCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            mobilCard.setBackground(Color.WHITE);
            mobilCard.setPreferredSize(new Dimension(220, 200)); // sedikit lebih kecil

            JPanel gambarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
            gambarPanel.setOpaque(false);
            JLabel lblGambar = new JLabel();
            lblGambar.setHorizontalAlignment(SwingConstants.CENTER);
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + mobil.getMerk().toLowerCase() + ".png"));
                Image original = icon.getImage();
                int targetWidth = 160;
                int targetHeight = (int) ((double) original.getHeight(null) / original.getWidth(null) * targetWidth);
                Image scaled = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                lblGambar.setIcon(new ImageIcon(scaled));
            } catch (Exception e) {
                lblGambar.setText("Gambar\nTidak Ada");
                lblGambar.setFont(new Font("Arial", Font.ITALIC, 10));
            }
            gambarPanel.add(lblGambar);

            // Nama mobil
            JLabel lblNama = new JLabel(mobil.getMerk() + " " + mobil.getTipe(), SwingConstants.CENTER);
            lblNama.setFont(new Font("Arial", Font.PLAIN, 12));

            // Panel tombol
            JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
            JButton btnDetail = new JButton("Deskripsi");
            JButton btnSewa = new JButton("Sewa");

            btnDetail.setFont(new Font("Arial", Font.PLAIN, 10));
            btnSewa.setFont(new Font("Arial", Font.PLAIN, 10));

            btnDetail.setMargin(new Insets(2, 6, 2, 6));
            btnSewa.setMargin(new Insets(2, 6, 2, 6));

            btnDetail.addActionListener(e -> frame.showDeskripsiMobil(mobil));
            btnSewa.addActionListener(e -> frame.showIsiBiodata(mobil));

            tombolPanel.add(btnDetail);
            tombolPanel.add(btnSewa);

            mobilCard.add(gambarPanel, BorderLayout.NORTH);
            mobilCard.add(lblNama, BorderLayout.CENTER);
            mobilCard.add(tombolPanel, BorderLayout.SOUTH);

            gridPanel.add(mobilCard);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        wrapperPanel.add(gridPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bawahPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKembali = new JButton("← Kembali");
        btnKembali.addActionListener(e -> frame.showIntro());
        bawahPanel.add(btnKembali);
        add(bawahPanel, BorderLayout.SOUTH);
    }
}
