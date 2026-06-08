package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_solo")
public class TipoSolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private String origemGeologica;


}
