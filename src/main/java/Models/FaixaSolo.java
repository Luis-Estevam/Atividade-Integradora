package Models;
import jakarta.persistence.*;

@Entity
@Table(name = "faixa_solo")
public class FaixaSolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double profundidade;
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "complexo_agricola_id")
    private ComplexoAgricola complexoAgricola;

    // Construtores, Getters e Setters
}
