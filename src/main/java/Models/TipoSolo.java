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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigemGeologica() {
        return origemGeologica;
    }

    public void setOrigemGeologica(String origemGeologica) {
        this.origemGeologica = origemGeologica;
    }
}
