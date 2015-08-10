/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.configuracao.negocio;

import java.io.Serializable;
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
@Table(name="tipodeprontuariodotipodeatendimento", uniqueConstraints = {
    @UniqueConstraint(columnNames={"tipodeprontuario_id", "tipodeatendimento_id"})
})
public class TipoDeProntuarioDoTipoDeAtendimento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "tipodeprontuario_id", referencedColumnName = "id", nullable = false)
    private TipoDeProntuario tipoDeProntuario;
    
    @ManyToOne
    @JoinColumn(name = "tipodeatendimento_id", referencedColumnName = "id", nullable = false)
    private TipoDeAtendimento tipoDeAtendimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoDeProntuario getTipoDeProntuario() {
        return tipoDeProntuario;
    }

    public void setTipoDeProntuario(TipoDeProntuario tipoDeProntuario) {
        this.tipoDeProntuario = tipoDeProntuario;
    }

    public TipoDeAtendimento getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }
}
