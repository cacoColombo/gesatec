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
@Table(name = "statusagendamento")
public class StatusAgendamento implements Serializable {
    
    @Transient
    public final int STATUS_AGENDA_AGUARDANDO_CONFIRMACAO = 1;
    
    @Transient
    public final int STATUS_AGENDA_AGENDADO = 2;
    
    @Transient
    public final int STATUS_AGENDA_REJEITADO = 3;
    
    @Transient
    public final int STATUS_AGENDA_CANCELADO = 4;
    
    @Transient
    public final int STATUS_AGENDA_EM_ANDAMENTO = 5;
    
    @Transient
    public final int STATUS_AGENDA_FINALIZADO = 6;
    
    @Transient
    public final String STATUS_AGENDA_LIVRE = "Livre";
    
    @Transient
    public final String STATUS_AGENDA_INDISPONIVEL = "Indisponível";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean encerraAgendamento;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean liberaHorario;    

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

    public boolean isEncerraAgendamento() {
        return encerraAgendamento;
    }

    public void setEncerraAgendamento(boolean encerraAgendamento) {
        this.encerraAgendamento = encerraAgendamento;
    }

    public boolean isLiberaHorario() {
        return liberaHorario;
    }

    public void setLiberaHorario(boolean liberaHorario) {
        this.liberaHorario = liberaHorario;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
