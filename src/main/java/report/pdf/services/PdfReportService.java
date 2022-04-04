package report.pdf.services;

import net.sf.jasperreports.engine.JRException;
import report.pdf.models.pdf.PdfReport;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface PdfReportService {
    PdfReport obtenerReporte(Map<String,Object> params) throws JRException, IOException, SQLException;
}
