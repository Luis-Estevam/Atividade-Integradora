package Models;
import jakarta.persistence.*;

@Entity
@Table(name = "contorno")
public class Contorno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String coordenadas;
    private double area;

    @ManyToOne
    @JoinColumn(name = "classe_solo_id")
    private ClasseSolo classeSolo;

    @ManyToOne
    @JoinColumn(name = "complexo_agricola_id")
    private ComplexoAgricola complexoAgricola;

    public Contorno() {
    }

    public Contorno(String coordenadas, double area, ClasseSolo classeSolo, ComplexoAgricola complexoAgricola) {
        this.coordenadas = coordenadas;
        this.area = area;
        this.classeSolo = classeSolo;
        this.complexoAgricola = complexoAgricola;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public ClasseSolo getClasseSolo() {
        return classeSolo;
    }

    public void setClasseSolo(ClasseSolo classeSolo) {
        this.classeSolo = classeSolo;
    }

    public ComplexoAgricola getComplexoAgricola() {
        return complexoAgricola;
    }

    public void setComplexoAgricola(ComplexoAgricola complexoAgricola) {
        this.complexoAgricola = complexoAgricola;
    }
}
