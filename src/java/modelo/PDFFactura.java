/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class PDFFactura {

    private List<Factura> getFacturas;

    public PDFFactura() {
    }

    public PDFFactura(List<Factura> getFacturas) {
        this.getFacturas = getFacturas;
    }

    public void writeHeader(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(6);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Id", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Correo", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Fecha", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Metodo", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Direccion", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Importe", font));
        pdfTable.addCell(cell);

    }

    public void writeTableData(PdfPTable pTable) {
        for (Factura factura : getFacturas) {
            pTable.addCell(factura.getFacnum() + "");
            pTable.addCell(factura.getCorusu());
            pTable.addCell(factura.getFacfec());
            pTable.addCell(factura.getMetpago());
            pTable.addCell(factura.getDirec());
            pTable.addCell(factura.getFacimp() + "");
        }
    }

    public void export(HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setSize(20);
            font.setColor(Color.black);
            //Titulo del archivo
            Paragraph paragraph = new Paragraph("Lista de Facturas", font);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1f, 4.5f, 3.0f, 2f, 3.5f, 1.5f});
            table.setSpacingBefore(10f);
            table.getDefaultCell().setFixedHeight(30f);

            writeHeader(table);
            writeTableData(table);

            document.add(table);

            document.close();
        } catch (Exception ex) {

        }
    }
}
