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
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import controlador.DAOProducto;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class PDFDetalle implements IPDF{

    private List<Factura> getFacturas;
    private List<Detalle> getDetalles;

    public PDFDetalle() {
    }

    public PDFDetalle(List<Factura> getFacturas, List<Detalle> getDetalles) {
        this.getFacturas = getFacturas;
        this.getDetalles = getDetalles;
    }
    
    @Override
    public void writeHeader(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();
        //cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(7);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("ID", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Descripción", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Valor Unitario", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Subtotal", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("IGV", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Precio Final", font));
        pdfTable.addCell(cell);

    }

    @Override
    public void writeTableData(PdfPTable pTable) {
        DAOProducto dpro = new DAOProducto();
        for (Detalle detalles : getDetalles) {
            try {
                pTable.addCell(detalles.getProcod());
                pTable.addCell(dpro.buscaProducto(detalles.getProcod()).getPronom());
                pTable.addCell(detalles.getCant() + "");
                pTable.addCell(detalles.getFacpre() * 0.82 + "");
                pTable.addCell(detalles.getFacpre() * 0.82 * detalles.getCant() + "");
                pTable.addCell(Math.round((detalles.getFacpre() * 0.18 * detalles.getCant()) * 100.0) / 100.0 + "");
                pTable.addCell(detalles.getFacpre() * detalles.getCant() + "");
            } catch (SQLException ex) {
                System.out.println("Error: "+ex.getMessage());
            }
        }
    }

    @Override
    public void export(HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(20);
            font.setColor(Color.black);
            //Titulo del archivo
            Paragraph paragraph = new Paragraph("NAGOYA RESTAURANT\t\t\t\t\t\t                  Nro Factura\nFACTURA ELECTRÓNICA\t\t\t\t\t\t                  0000" + getFacturas.get(0).getFacnum(), font);
            //paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);

            contentByte.rectangle(350, document.getPageSize().getHeight() - 130, document.getPageSize().getWidth() - 390,
                    100);
            contentByte.stroke();

            Font fontsub = FontFactory.getFont(FontFactory.HELVETICA);
            fontsub.setSize(14);
            Paragraph subtitle = new Paragraph("Circunvalación del Golf Los Incas 154 Camacho\nSANTIAGO DE SURCO - LIMA", fontsub);
            document.add(subtitle);
            // Agregar una línea divisora
            LineSeparator lineaDivisora = new LineSeparator();
            lineaDivisora.setLineColor(Color.BLACK);
            lineaDivisora.setLineWidth(1.5f);
            Chunk chunkLineaDivisora = new Chunk(lineaDivisora);
            document.add(chunkLineaDivisora);

            //document.add(Chunk.NEWLINE);
            String facText = "Fecha de Emisión\t: " + getFacturas.get(0).getFacfec() + "\nCliente(a)\t: " + getFacturas.get(0).getCorusu() + "\nEstablecimiento\t: " + getFacturas.get(0).getDirec()
                    + "\nMétodo de pago\t: " + getFacturas.get(0).getMetpago() + "\nObservación\t: ";
            Paragraph contenido = new Paragraph(facText, fontsub);
            document.add(contenido);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1f, 2.5f, 1.5f, 1.5f, 1.5f, 1f, 1f});
            table.setSpacingBefore(10f);
            table.getDefaultCell().setFixedHeight(30f);

            writeHeader(table);
            writeTableData(table);

            document.add(table);

            document.add(Chunk.NEWLINE);
            Paragraph text1 = new Paragraph("Valor total de la venta: S/." + getFacturas.get(0).getFacimp(), fontsub);
            //document.add(text1);
            PdfPCell cell1 = new PdfPCell(new Paragraph());
            cell1.addElement(text1);
            cell1.setBorderWidth(1); // Establecer el ancho del borde
            // Crear una tabla y agregar la celda
            PdfPTable table1 = new PdfPTable(1);
            table1.getDefaultCell().setFixedHeight(30f);
            table1.setWidthPercentage(40); // Establecer el ancho de la tabla al 100% del ancho de la página
            table1.addCell(cell1);
            table1.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
            // Agregar la tabla al documento
            document.add(table1);

            contentByte.rectangle(15, 15, document.getPageSize().getWidth() - 30,
                    document.getPageSize().getHeight() - 30);
            contentByte.stroke();

            document.close();
        } catch (Exception ex) {

        }
    }


}
