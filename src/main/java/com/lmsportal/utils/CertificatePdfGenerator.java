package com.lmsportal.utils;

import com.lmsportal.models.Certificate;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class CertificatePdfGenerator {

    public byte[] generate(Certificate certificate) {

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
            PdfWriter.getInstance(document, out);
            document.open();

            // ===== FONTS =====
            Font titleFont = new Font(Font.HELVETICA, 30, Font.BOLD);
            Font nameFont = new Font(Font.HELVETICA, 22, Font.BOLD);
            Font bodyFont = new Font(Font.HELVETICA, 16);
            Font smallFont = new Font(Font.HELVETICA, 12);

            // ===== BORDER =====
            Rectangle border = new Rectangle(
                    document.left(),
                    document.bottom(),
                    document.right(),
                    document.top()
            );
            border.setBorder(Rectangle.BOX);
            border.setBorderWidth(4);
            document.add(border);

            // ===== TITLE =====
            Paragraph title = new Paragraph("CERTIFICATE OF COMPLETION", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(40);
            document.add(title);

            // ===== BODY =====
            Paragraph text = new Paragraph("This is proudly presented to", bodyFont);
            text.setAlignment(Element.ALIGN_CENTER);
            text.setSpacingAfter(20);
            document.add(text);

            // ===== STUDENT NAME =====
            Paragraph name = new Paragraph(certificate.getStudent().getName(), nameFont);
            name.setAlignment(Element.ALIGN_CENTER);
            name.setSpacingAfter(25);
            document.add(name);

            // ===== COURSE =====
            Paragraph course = new Paragraph(
                    "For successfully completing the course\n\n" +
                    certificate.getCourse().getTitle(),
                    bodyFont
            );
            course.setAlignment(Element.ALIGN_CENTER);
            course.setSpacingAfter(40);
            document.add(course);

            // ===== FOOTER =====
            Paragraph footer = new Paragraph(
                    "Certificate ID: " + certificate.getCertificateCode() +
                    "\nIssued on: " + certificate.getIssueDate().toLocalDate(),
                    smallFont
            );
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate certificate PDF", e);
        }
    }
}
