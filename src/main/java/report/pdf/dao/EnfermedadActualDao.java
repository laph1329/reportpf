package report.pdf.dao;


import org.springframework.data.repository.CrudRepository;
import report.pdf.models.pdf.EnfermedadActual;

public interface EnfermedadActualDao extends CrudRepository<EnfermedadActual,String> {
}
