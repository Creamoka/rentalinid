package com.example.rentalin.id.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.example.rentalin.id.database.DatabaseHandler;
import com.example.rentalin.id.model.Transaksi;

public class HalamanRiwayatPanel extends JPanel {

    private MainFrame frame;
    private JPanel daftarPanel;
    private JScrollPane scrollPane;

    public HalamanRiwayatPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblJudul = new JLabel("Riwayat Transaksi");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblJudul, BorderLayout.NORTH);

        daftarPanel = new JPanel();
        daftarPanel.setLayout(new BoxLayout(daftarPanel, BoxLayout.Y_AXIS));
        daftarPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(daftarPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Panel bawah
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton btnKembali = new JButton("← Kembali");
        btnKembali.addActionListener(e -> frame.showIntro());
        bottomPanel.add(btnKembali, BorderLayout.WEST);

        JButton btnHapusSemua = new JButton("🧹 Hapus Semua Riwayat");
        btnHapusSemua.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Semua riwayat akan dihapus. Yakin?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                DatabaseHandler.hapusSemuaTransaksi();
                tampilkanTransaksi();
            }
        });
        bottomPanel.add(btnHapusSemua, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        tampilkanTransaksi();
    }

    public void tampilkanTransaksi() {
        daftarPanel.removeAll();
        List<Transaksi> transaksiList = DatabaseHandler.ambilSemuaTransaksi();

        if (transaksiList.isEmpty()) {
            JLabel kosong = new JLabel("Belum ada transaksi.");
            kosong.setFont(new Font("Arial", Font.ITALIC, 14));
            kosong.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel kosongPanel = new JPanel(new BorderLayout());
            kosongPanel.setBackground(Color.WHITE);
            kosongPanel.add(kosong, BorderLayout.CENTER);
            daftarPanel.add(kosongPanel);
        } else {
            for (Transaksi t : transaksiList) {
                JPanel card = new JPanel(new BorderLayout(10, 10));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                card.setBackground(Color.WHITE);
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

                JLabel isi = new JLabel("<html><b>" + t.getPelanggan().getNama() + "</b> menyewa <b>"
                        + t.getMobil().getMerk() + " " + t.getMobil().getTipe()
                        + "</b> selama " + t.getLamaSewa() + " hari<br>Total: Rp " + t.getTotalHarga() + "</html>");
                isi.setFont(new Font("Arial", Font.PLAIN, 13));

                JButton btnHapus = new JButton("🗑 Hapus");
                btnHapus.addActionListener(e -> {
                    int konfirmasi = JOptionPane.showConfirmDialog(this,
                        "Hapus transaksi ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        DatabaseHandler.hapusTransaksiTerakhir();
                        tampilkanTransaksi();
                    }
                });

                card.add(isi, BorderLayout.CENTER);
                card.add(btnHapus, BorderLayout.EAST);

                daftarPanel.add(card);
                daftarPanel.add(Box.createVerticalStrut(10));
            }
        }

        daftarPanel.revalidate();
        daftarPanel.repaint();
    }
}
