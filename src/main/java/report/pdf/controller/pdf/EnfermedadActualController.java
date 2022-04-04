package report.pdf.controller.pdf;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import report.pdf.models.pdf.EnfermedadActual;
import report.pdf.services.EnfermedadActualService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/apienfermedad")
public class EnfermedadActualController {

    @Autowired
    private EnfermedadActualService enfermedadActualService;

    @GetMapping("/lista")
    public List<EnfermedadActual> listar(){
        return enfermedadActualService.findAll();
    }

    @PostMapping("/lista")
    public EnfermedadActual guardar(@RequestBody EnfermedadActual enfermedadActual){
        return enfermedadActualService.save(enfermedadActual);
    }
    @GetMapping("/tareas/{id}")
    public EnfermedadActual getDatosEmergencia(@PathVariable String id){
        return enfermedadActualService.findById(id);
    }

    @PutMapping(value="/listar/{id}")
    public EnfermedadActual actualizarAntecedentes(@PathVariable long id, @RequestBody EnfermedadActual enfermedadActual){
        return enfermedadActualService.save(enfermedadActual);
    }

}
