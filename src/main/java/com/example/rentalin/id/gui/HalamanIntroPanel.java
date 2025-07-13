package com.example.rentalin.id.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HalamanIntroPanel extends JPanel {

    public HalamanIntroPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel Tengah (judul, subjudul, tombol sewa & tentang kami)
        JPanel panelTengah = new JPanel(new GridBagLayout());
        panelTengah.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel lblTitle = new JLabel("🚗 Rentalin Mobil");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        panelTengah.add(lblTitle, gbc);

        JLabel lblSub = new JLabel("Sewa mobil dengan cepat, mudah, dan terpercaya");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSub.setForeground(Color.DARK_GRAY);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        panelTengah.add(lblSub, gbc);

        gbc.gridy = 2;
        panelTengah.add(Box.createVerticalStrut(30), gbc);

        JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        tombolPanel.setBackground(Color.WHITE);

        JButton btnSewa = new JButton("Mulai Sewa");
        JButton btnTentang = new JButton("Tentang Kami");

        Font tombolFont = new Font("SansSerif", Font.PLAIN, 14);
        Dimension tombolSize = new Dimension(130, 40);

        btnSewa.setFont(tombolFont);
        btnSewa.setPreferredSize(tombolSize);
        btnTentang.setFont(tombolFont);
        btnTentang.setPreferredSize(tombolSize);

        btnSewa.addActionListener(e -> frame.showPilihMobil());
        btnTentang.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Rentalin adalah aplikasi penyewaan mobil sederhana yang dibuat sepenuh hati\n\nDeveloper: Arrafi Hilmi\nArya Maulana Yusuf\nHarsya Vil Ardi\nRaihan Khadafi\nSidqi Rafi Al Fauzan\nSyifa Aulia Fitri",
                "Tentang Kami", JOptionPane.INFORMATION_MESSAGE));

        tombolPanel.add(btnSewa);
        tombolPanel.add(btnTentang);

        gbc.gridy = 3;
        panelTengah.add(tombolPanel, gbc);

        add(panelTengah, BorderLayout.CENTER);

        // Panel Riwayat di kanan bawah
        JPanel bawahKanan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawahKanan.setBackground(Color.WHITE);
        JButton btnRiwayat = new JButton("Riwayat Transaksi");
        btnRiwayat.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnRiwayat.setPreferredSize(new Dimension(160, 30));

        btnRiwayat.addActionListener(e -> frame.showRiwayat());

        bawahKanan.add(btnRiwayat);
        add(bawahKanan, BorderLayout.SOUTH);
    }
}
