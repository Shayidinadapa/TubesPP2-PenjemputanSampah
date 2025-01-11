package id.com.ewaste.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import id.com.ewaste.controller.KategoriController;
import id.com.ewaste.controller.PenjemputanController;
import id.com.ewaste.model.Penjemputan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class HistoryPanel extends JPanel {
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private PenjemputanController penjemputanController;

    public HistoryPanel() {
        penjemputanController = new PenjemputanController();
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Kategori", "Jumlah (Kg)", "Alamat", "Tanggal"}, 0);
        historyTable = new JTable(tableModel);
        add(new JScrollPane(historyTable), BorderLayout.CENTER);

        // Title
        JLabel titleLabel = new JLabel("Riwayat Penjemputan Sampah", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Add PDF Button
        JButton generatePdfButton = new JButton("Generate PDF Report");
        generatePdfButton.addActionListener(e -> generatePdfReport());
        add(generatePdfButton, BorderLayout.SOUTH);

        // Load the history data
        loadHistory();

        // Listen for when this panel is displayed
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                // Load history whenever the panel is displayed
                loadHistory();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });
    }

    private void loadHistory() {
        ArrayList<Penjemputan> historyList = penjemputanController.getPenjemputanHistory();
        tableModel.setRowCount(0); // Clear table
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Penjemputan p : historyList) {
            tableModel.addRow(new Object[] {
                p.getId(),
                getKategoriName(p.getKategoriId()), // Convert kategori ID to name
                p.getJumlah(),
                p.getAlamat(),
                sdf.format(p.getTanggal())
            });
        }
    }

    private String getKategoriName(int kategoriId) {
        // This method converts kategori ID to its name (You can optimize by caching category names)
        return new KategoriController().getAllKategori()
                .stream()
                .filter(k -> k.getId() == kategoriId)
                .map(k -> k.getNama())
                .findFirst()
                .orElse("Unknown");
    }

    private void generatePdfReport() {
        Document document = new Document();
        try {
            // PDF file path
            String filePath = "PenjemputanHistoryReport.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open document
            document.open();

            // Title
            Paragraph title = new Paragraph("Riwayat Penjemputan Sampah", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Create table with 5 columns
            PdfPTable pdfTable = new PdfPTable(5);
            pdfTable.setWidths(new float[]{1, 2, 2, 3, 2});

            // Add headers
            pdfTable.addCell("ID");
            pdfTable.addCell("Kategori");
            pdfTable.addCell("Jumlah (Kg)");
            pdfTable.addCell("Alamat");
            pdfTable.addCell("Tanggal");

            // Add data rows
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                pdfTable.addCell(String.valueOf(tableModel.getValueAt(i, 0))); // ID
                pdfTable.addCell(String.valueOf(tableModel.getValueAt(i, 1))); // Kategori
                pdfTable.addCell(String.valueOf(tableModel.getValueAt(i, 2))); // Jumlah (Kg)
                pdfTable.addCell(String.valueOf(tableModel.getValueAt(i, 3))); // Alamat
                pdfTable.addCell(String.valueOf(tableModel.getValueAt(i, 4))); // Tanggal
            }

            // Add table to document
            document.add(pdfTable);

            // Close the document
            document.close();

            // Notify the user
            JOptionPane.showMessageDialog(this, "PDF Report Generated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating PDF report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
