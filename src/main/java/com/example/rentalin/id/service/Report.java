package com.example.rentalin.id.service;

import com.example.rentalin.id.model.Transaksi;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Report {

    public static void cetakRiwayatKePDF(List<Transaksi> transaksiList, String filePath) {
        if (transaksiList == null || transaksiList.isEmpty()) {
            System.out.println("Tidak ada data transaksi untuk dicetak.");
            return;
        }

        if (filePath == null || filePath.isEmpty()) {
            filePath = "riwayat_transaksi.pdf";
        }

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Judul
            Paragraph title = new Paragraph("🧾 Riwayat Transaksi Rentalin ID", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Tabel
            PdfPTable table = new PdfPTable(6); // 6 kolom
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 3, 2, 1.5f, 2, 2}); // lebar relatif per kolom

            // Header
            String[] headers = {"Tanggal", "Nama (NIK)", "Mobil", "Hari", "Metode", "Total"};
            for (String h : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(h, headerFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(headerCell);
            }

            // Isi
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String today = LocalDate.now().format(formatter); // placeholder tanggal

            for (Transaksi t : transaksiList) {
                table.addCell(new Phrase(today, bodyFont)); // ganti ini kalau ada tanggal di model
                table.addCell(new Phrase(t.getPelanggan().getNama() + " (" + t.getPelanggan().getNik() + ")", bodyFont));
                table.addCell(new Phrase(t.getMobil().getMerk() + " " + t.getMobil().getTipe(), bodyFont));
                table.addCell(new Phrase(String.valueOf(t.getLamaSewa()), bodyFont));
                table.addCell(new Phrase(t.getMetodePembayaran(), bodyFont));
                table.addCell(new Phrase("Rp " + String.format("%,d", t.getTotalHarga()), bodyFont));
            }

            document.add(table);
            document.close();

            System.out.println("PDF berhasil dibuat di: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
