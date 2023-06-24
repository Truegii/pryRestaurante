package modelo;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class PDFReservaDetalle {

    private List<Reservas> getReservas;
    private ArrayList<String> usuarios;

    public PDFReservaDetalle() {
    }

    public PDFReservaDetalle(List<Reservas> getReservas, ArrayList<String> usuarios) {
        this.getReservas = getReservas;
        this.usuarios = usuarios;
    }


    public void writeHeader(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();
        //cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("ID", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cliente", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Fecha", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Actividad", font));
        pdfTable.addCell(cell);

    }

    public void writeTableData(PdfPTable pTable) throws SQLException {
        for (Reservas reservas : getReservas) {
            pTable.addCell(reservas.getIdreserva() + "");
            pTable.addCell(reservas.getCorreo());
            pTable.addCell(reservas.getFecha());
            pTable.addCell(reservas.getCantidad() + "");
            pTable.addCell(reservas.getActividad());
        }
    }

    public void export(HttpServletResponse response) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(20);
            font.setColor(Color.black);
            //Titulo del archivo
            Paragraph paragraph = new Paragraph("NAGOYA RESTAURANT\t\t\t\t\t\t                  Nro Reserva\nRESERVA ELECTRÓNICA\t\t\t\t\t\t                  0000" + getReservas.get(0).getIdreserva(), font);
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
            String facText = "Fecha de Reserva\t: " + getReservas.get(0).getFecha() + "\nCliente(a)\t: " + usuarios.get(1)+" "+usuarios.get(2)+" "+usuarios.get(3) + "\nDNI\t: " + usuarios.get(5)+ "\nEstablecimiento\t: Circunvalación del Golf Los Incas 154 Camacho"
                    + "\nMétodo de pago\t: Efectivo" + "\nObservación\t: ";
            Paragraph contenido = new Paragraph(facText, fontsub);
            document.add(contenido);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1f, 2.5f, 1.5f, 1.5f, 1.5f});
            table.setSpacingBefore(10f);
            table.getDefaultCell().setFixedHeight(30f);

            writeHeader(table);
            writeTableData(table);

            document.add(table);

            document.add(Chunk.NEWLINE);
            String valorReserva="Valor total de la reserva: S/.";
            if (getReservas.get(0).getActividad().equals("Pedida de mano")) {
                valorReserva= valorReserva+"130";
            } else if (getReservas.get(0).getActividad().equals("Dia festivo")) {
                valorReserva= valorReserva+"100";
            } else if(getReservas.get(0).getActividad().equals("Cumpleanos")){
                valorReserva= valorReserva+"200";
            } else if(getReservas.get(0).getActividad().equals("Ocasion especial")){
                valorReserva= valorReserva+"70";
            }
            Paragraph text1 = new Paragraph(valorReserva,fontsub);
//            document.add(text1);
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

            document.add(chunkLineaDivisora);
            Paragraph textbot = new Paragraph("Presentar este documento al momento de ingresar al local.",fontsub);
            document.add(textbot);
            
            
            contentByte.rectangle(15, 15, document.getPageSize().getWidth() - 30,
                    document.getPageSize().getHeight() - 30);
            contentByte.stroke();

            document.close();
        } catch (Exception ex) {

        }
    }
}
