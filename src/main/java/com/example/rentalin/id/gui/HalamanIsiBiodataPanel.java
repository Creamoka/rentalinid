package com.example.rentalin.id.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.rentalin.id.database.DatabaseHandler;
import com.example.rentalin.id.model.Mobil;
import com.example.rentalin.id.model.Pelanggan;
import com.example.rentalin.id.model.Transaksi;

public class HalamanIsiBiodataPanel extends JPanel {

    private MainFrame frame;
    private Mobil mobil;

    private JTextField tfNama, tfNIK, tfLamaSewa;
    private JComboBox<String> cbJenisPembayaran;

    public HalamanIsiBiodataPanel(MainFrame frame) {
        this.frame = frame;
        this.mobil = null;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblHeader = new JLabel("Isi Biodata dan Metode Pembayaran");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblHeader, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfNama = new JTextField(20);
        tfNIK = new JTextField(20);
        tfLamaSewa = new JTextField(5);
        cbJenisPembayaran = new JComboBox<>(new String[] {
            "Transfer Bank", "Kartu Kredit", "E-Wallet", "Bayar di Tempat"
        });

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nama Penyewa:"), gbc);
        gbc.gridx = 1;
        formPanel.add(tfNama, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("NIK:"), gbc);
        gbc.gridx = 1;
        formPanel.add(tfNIK, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Lama Sewa (hari):"), gbc);
        gbc.gridx = 1;
        formPanel.add(tfLamaSewa, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Jenis Pembayaran:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbJenisPembayaran, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKembali = new JButton("← Kembali");
        JButton btnSewa = new JButton("Konfirmasi Sewa");

        btnKembali.addActionListener(e -> frame.showDeskripsiMobil(mobil));

        btnSewa.addActionListener(e -> {
            String nama = tfNama.getText();
            String nik = tfNIK.getText();
            String lamaSewaStr = tfLamaSewa.getText();
            String metode = cbJenisPembayaran.getSelectedItem().toString();

            if (nama.isEmpty() || nik.isEmpty() || lamaSewaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int lama;
            try {
                lama = Integer.parseInt(lamaSewaStr);
                if (lama <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lama sewa harus berupa angka positif.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pelanggan p = new Pelanggan(nama, nik);
            int id = DatabaseHandler.insertPelanggan(p);
            if (id == -1) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data pelanggan ke database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            p.setId(id);

            Transaksi t = new Transaksi(p, mobil, lama, metode);

            frame.sistem.tambahTransaksi(t);

            JOptionPane.showMessageDialog(this,
                "Transaksi berhasil!\nTotal: Rp" + t.hitungTotalHarga(),
                "Sukses", JOptionPane.INFORMATION_MESSAGE);

            frame.showIntro();
        });

        buttonPanel.add(btnKembali);
        buttonPanel.add(btnSewa);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
        tfNama.setText("");
        tfNIK.setText("");
        tfLamaSewa.setText("");
        cbJenisPembayaran.setSelectedIndex(0);
    }
}
