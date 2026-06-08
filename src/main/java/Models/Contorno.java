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


}
