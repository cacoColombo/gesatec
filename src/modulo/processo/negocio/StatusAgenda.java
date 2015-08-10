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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "statusagenda")
public class StatusAgenda implements Serializable {
    
    @Transient
    final int STATUS_AGENDA_AGENDADO = 1;
    
    @Transient
    final int STATUS_AGENDA_CONFIRMADO = 2;
    
    @Transient
    final int STATUS_AGENDA_EM_ANDAMENTO = 3;
    
    @Transient
    final int STATUS_AGENDA_FINALIZADO = 4;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(unique = true, nullable = false)
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
