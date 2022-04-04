package report.pdf.services;



import report.pdf.models.pdf.EnfermedadActual;

import java.util.List;

public interface EnfermedadActualService {
    public List<EnfermedadActual> findAll();
    public EnfermedadActual save(EnfermedadActual enfermedadActual);
    public EnfermedadActual findById(String id);
    public void delete(String id);
}
