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

    public FaixaSolo() {
    }

    public FaixaSolo(double profundidade, String localizacao, ComplexoAgricola complexoAgricola) {
        this.profundidade = profundidade;
        this.localizacao = localizacao;
        this.complexoAgricola = complexoAgricola;
    }

    public double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public ComplexoAgricola getComplexoAgricola() {
        return complexoAgricola;
    }

    public void setComplexoAgricola(ComplexoAgricola complexoAgricola) {
        this.complexoAgricola = complexoAgricola;
    }
}
