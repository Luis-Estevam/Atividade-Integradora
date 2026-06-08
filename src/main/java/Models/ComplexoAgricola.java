package Models;

import jakarta.persistence.*;

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


}
