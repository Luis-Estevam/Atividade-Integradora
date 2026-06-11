package Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "complexos")
public class ComplexoAgricola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double area;

    private String utilizacao;

    @OneToMany(mappedBy = "complexoAgricola", cascade = CascadeType.ALL)
    private List<Contorno> contornos;

    @OneToMany(mappedBy = "complexoAgricola", cascade = CascadeType.ALL)
    private List<FaixaSolo> faixasSolo;

    public ComplexoAgricola() {
        this.contornos = new ArrayList<>();
        this.faixasSolo = new ArrayList<>();
    }

    public ComplexoAgricola(String nome, double area, String utilizacao) {
        this();
        this.nome = nome;
        this.area = area;
        this.utilizacao = utilizacao;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(String utilizacao) {
        this.utilizacao = utilizacao;
    }

}
