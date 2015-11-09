/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.negocio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="especializacaodoprofissional", uniqueConstraints = {
    @UniqueConstraint(columnNames={"profissional_id", "especializacao_id"})
})
public class EspecializacaoDoProfissional implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name = "profissional_id", referencedColumnName = "id", nullable = false)
    private Profissional profissional;
    
    @ManyToOne
    @JoinColumn(name = "especializacao_id", referencedColumnName = "id", nullable = false)
    private Especializacao especializacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(Especializacao especializacao) {
        this.especializacao = especializacao;
    }
}
