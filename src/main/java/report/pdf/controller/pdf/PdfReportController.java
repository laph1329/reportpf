package report.pdf.controller.pdf;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.pdf.enums.TipoReporteEnum;
import report.pdf.models.pdf.PdfReport;
import report.pdf.services.PdfReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/reporte")
public class PdfReportController {
    @Autowired
    private PdfReportService pdfReportService;
    @GetMapping(path="/report/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException{
        PdfReport dto= pdfReportService.obtenerReporte(params);
        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }

    @GetMapping(path = "/ficha")
    public ResponseEntity<Resource> downloadPdf(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        PdfReport dto = pdfReportService.obtenerReporte(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }



    @Autowired
    private DataSource dataSource;

    @RequestMapping("/{reportName}")
    public void demoReport1(@PathVariable("reportName") final String reportName,
                            @RequestParam(required = false) Map<String, Object> parameters,
                            HttpServletResponse response, HttpServletRequest request) throws  Exception{
        parameters = parameters == null ? new HashMap<>() : parameters;
        // Obtener la secuencia del archivo
        ClassPathResource resource = new ClassPathResource("reports" + File.separator + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        // Vista previa de PDF en línea
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        // Generar documento pdf
        /*String fileName = "F:/filename.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,fileName);*/

        // Generar documento de Word
       /* String fileName = "F:/filename.doc";
        JRRtfExporter docExporter = new JRRtfExporter();
        docExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,fileName);
        docExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docExporter.exportReport();*/

        // Generar documento de Excel
        /*JRXlsExporter excel = new JRXlsExporter();
        System.out.println(request.getServletContext().getRealPath("jaspers/demoreport1.jasper"));
        String fileName = "F://filename.xls";
        excel.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,fileName);
        excel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        excel.exportReport();*/

    }

}
