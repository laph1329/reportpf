package report.pdf.services;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pdf.commons.JasperReportManager;
import report.pdf.enums.TipoReporteEnum;
import report.pdf.models.pdf.PdfReport;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class PdfReportServiceImpl implements PdfReportService {
    @Autowired
    private JasperReportManager reportManager;
    @Autowired
    private DataSource dataSource;
    @Override
    public PdfReport obtenerReporte(Map<String, Object> params)
            throws JRException, IOException, SQLException {
        String fileName="samuUnaFicha2";
        PdfReport dto= new PdfReport();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }
}
