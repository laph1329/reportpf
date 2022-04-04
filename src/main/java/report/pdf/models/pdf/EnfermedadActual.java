package report.pdf.models.pdf;

import javax.persistence.*;

@Entity
@Table(name = "ficha_enfermedad_actual")
public class EnfermedadActual {
    @Id
    @Column(name = "id_enfermedad_actual")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_enfermedad_actual;
    @Column(name = "tiempo_enfermedad")
    private String tiempo_enfermedad;
    @Column(name = "inicio")
    private String inicio;
    @Column(name = "curso")
    private String curso;

    public Long getId_enfermedad_actual() {
        return id_enfermedad_actual;
    }

    public void setId_enfermedad_actual(Long id_enfermedad_actual) {
        this.id_enfermedad_actual = id_enfermedad_actual;
    }

    public String getTiempo_enfermedad() {
        return tiempo_enfermedad;
    }

    public void setTiempo_enfermedad(String tiempo_enfermedad) {
        this.tiempo_enfermedad = tiempo_enfermedad;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
