//package com.apna.bank.Controller;
//
//import com.apna.bank.Entity.Transaction;
//import com.apna.bank.Service.PDFService;
//import com.apna.bank.repository.TransctionRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/statement")
//public class StatementController {
//
//    private final TransctionRepository transctionRepository;
//    private final PDFService pdfService;
//
//    @GetMapping("/pdf/{accountNumber}")
//    public ResponseEntity<byte[]> generatePDFStatement(@PathVariable String accountNumber) throws IOException {
//
//        // 1. Get all transactions of that account
//        List<Transaction> transactions =
//                transctionRepository.findByFromAccountNumberOrToAccountNumber(accountNumber, accountNumber);
//
//        // 2. Generate PDF
//        ByteArrayInputStream bis = pdfService.generateTransactionReport(transactions);
//
//        // 3. Convert InputStream to byte[]
//        byte[] pdfBytes = bis.readAllBytes();
//
//        // 4. Set HTTP headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=bank_statement.pdf");
//
//        // 5. Return PDF as response
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(pdfBytes);
//}
//}