/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import com.lowagie.text.pdf.PdfPTable;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public interface IPDF {
    public void writeHeader(PdfPTable pdfTable);
    public void writeTableData(PdfPTable pTable);
    public void export(HttpServletResponse response);
}
