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
import modulo.configuracao.negocio.CampoDoTipoDeProntuario;

@Entity
@Table(name = "registronocampodoprontuario")
public class RegistroNoCampoDoProntuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id", nullable = false)
    private Prontuario prontuario;
    
    @ManyToOne
    @JoinColumn(name = "campodotipodeprontuario_id", referencedColumnName = "id", nullable = false)
    private CampoDoTipoDeProntuario campoDoTipoDeProntuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public CampoDoTipoDeProntuario getCampoDoTipoDeProntuario() {
        return campoDoTipoDeProntuario;
    }

    public void setCampoDoTipoDeProntuario(CampoDoTipoDeProntuario campoDoTipoDeProntuario) {
        this.campoDoTipoDeProntuario = campoDoTipoDeProntuario;
    }
}
