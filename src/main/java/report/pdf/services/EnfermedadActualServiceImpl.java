package report.pdf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import report.pdf.dao.EnfermedadActualDao;
import report.pdf.models.pdf.EnfermedadActual;

import java.util.List;

@Service
public class EnfermedadActualServiceImpl implements  EnfermedadActualService{

    @Autowired
    private EnfermedadActualDao enfermedadActualDao;

    @Override
    @Transactional(readOnly = true)
    public List<EnfermedadActual> findAll() {
        return (List<EnfermedadActual>) enfermedadActualDao.findAll() ;
    }

    @Override
    @Transactional(readOnly = false)
    public EnfermedadActual save(EnfermedadActual enfermedadActual) {
        return enfermedadActualDao.save(enfermedadActual);
    }

    @Override
    @Transactional(readOnly = true)
    public EnfermedadActual findById(String id) {
        return enfermedadActualDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {
        enfermedadActualDao.deleteById(id);
    }
}
