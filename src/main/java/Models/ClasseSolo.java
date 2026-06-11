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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPropriedadesFisicas() {
        return propriedadesFisicas;
    }

    public void setPropriedadesFisicas(String propriedadesFisicas) {
        this.propriedadesFisicas = propriedadesFisicas;
    }

    public TipoSolo getTipoSolo() {
        return tipoSolo;
    }

    public void setTipoSolo(TipoSolo tipoSolo) {
        this.tipoSolo = tipoSolo;
    }
}
