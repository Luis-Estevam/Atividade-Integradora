package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "classe_solo")
public class ClasseSolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String propriedadesFisicas;

    @ManyToOne
    @JoinColumn(name = "tipo_solo_id")
    private TipoSolo tipoSolo;


}
