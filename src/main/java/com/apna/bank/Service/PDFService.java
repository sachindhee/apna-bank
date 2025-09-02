//package com.apna.bank.Service;
//
//import com.apna.bank.Entity.Transaction;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//import java.util.stream.Stream;
//
//@Component
//public class PDFService {
//
//    public ByteArrayInputStream generateTransactionReport(List<Transaction> transactions) {
//
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
//            Paragraph para = new Paragraph("Bank Statement", font);
//            para.setAlignment(Element.ALIGN_CENTER);
//            document.add(para);
//
//            document.add(Chunk.NEWLINE);
//
//            PdfPTable table = new PdfPTable(4);
//            table.setWidthPercentage(100);
//            table.setWidths(new int[]{4, 4, 3, 4});
//
//            Stream.of("Txn Type", "Amount", "Date", "Description")
//                    .forEach(headerTitle -> {
//                        PdfPCell header = new PdfPCell();
//                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                        header.setPhrase(new Phrase(headerTitle));
//                        table.addCell(header);
//                    });
//
//            for (Transaction txn : transactions) {
//                table.addCell(txn.getType());
//                table.addCell(String.valueOf(txn.getAmount()));
//                table.addCell(String.valueOf(txn.getLocalDateTime()));         // ✅ Correct Date
//                table.addCell(txn.getDescription());
//            }
//
//            document.add(table);
//            document.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//}
//}