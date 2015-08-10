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

@Entity
@Table(name = "campodotipodeprontuario")
public class CampoDoTipoDeProntuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "tipodeprontuario_id", referencedColumnName = "id", nullable = false)
    private TipoDeProntuario tipoDeProntuario;
    
    @ManyToOne
    @JoinColumn(name = "campo_id", referencedColumnName = "id", nullable = false)
    private Campo campo;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean requerido;
}
