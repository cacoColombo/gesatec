/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import modulo.cadastro.negocio.Cliente;
import modulo.cadastro.negocio.Profissional;
import modulo.configuracao.negocio.TipoDeProntuarioDoTipoDeAtendimento;

@Entity
@Table(name = "prontuario")
public class Prontuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "profissional_id", referencedColumnName = "id", nullable = false)
    private Profissional profissional;
    
    @ManyToOne
    @JoinColumn(name = "atendimento_id", referencedColumnName = "id", nullable = false)
    private Atendimento atendimento;
    
    @ManyToOne
    @JoinColumn(name = "tipodeprontuariodotipodeatendimento_id", referencedColumnName = "id", nullable = false)
    private TipoDeProntuarioDoTipoDeAtendimento tipoDeProntuarioDoTipoDeAtendimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public TipoDeProntuarioDoTipoDeAtendimento getTipoDeProntuarioDoTipoDeAtendimento() {
        return tipoDeProntuarioDoTipoDeAtendimento;
    }

    public void setTipoDeProntuarioDoTipoDeAtendimento(TipoDeProntuarioDoTipoDeAtendimento tipoDeProntuarioDoTipoDeAtendimento) {
        this.tipoDeProntuarioDoTipoDeAtendimento = tipoDeProntuarioDoTipoDeAtendimento;
    }
}
