/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Factory.IPDF;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class PDFReserva implements IPDF{

    private List<Reservas> getReservas;
    private ArrayList<String> usuarios;

    public PDFReserva() {
    }

    public PDFReserva(List<Reservas> getReservas, ArrayList<String> usuarios) {
        this.getReservas = getReservas;
        this.usuarios = usuarios;
    }

    @Override
    public void writeHeader(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(7);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Id", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cliente", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("DNI", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Correo", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Fecha", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cant", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Actividad", font));
        pdfTable.addCell(cell);

    }

    @Override
    public void writeTableData(PdfPTable pTable) {
        for (Reservas reserva : getReservas) {
            pTable.addCell(reserva.getIdreserva() + "");
            pTable.addCell(usuarios.get(1) + " " + usuarios.get(2) + " " + usuarios.get(3));
            pTable.addCell(usuarios.get(5));
            pTable.addCell(reserva.getCorreo());
            pTable.addCell(reserva.getFecha());
            pTable.addCell(reserva.getCantidad() + "");
            pTable.addCell(reserva.getActividad());
            
        }   
    }

    @Override
    public void export(HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setSize(20);
            font.setColor(Color.black);
            //Titulo del archivo
            Paragraph paragraph = new Paragraph("Lista de Reservas", font);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            //Linea en blanco
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1f, 3.5f, 2.5f, 4.5f, 2.2f, 1.5f, 2.0f});
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
