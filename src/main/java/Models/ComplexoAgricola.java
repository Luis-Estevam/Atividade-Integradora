package Models;

import jakarta.persistence.*;

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


}
