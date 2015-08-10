/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.negocio;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "agenda_id", referencedColumnName = "id", nullable = false)
    private Agenda agenda;
    
    @Column(nullable = false)
    private Time horarioRealIniciado;
    
    @Column(nullable = false)
    private Time horarioRealEncerrado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Time getHorarioRealIniciado() {
        return horarioRealIniciado;
    }

    public void setHorarioRealIniciado(Time horarioRealIniciado) {
        this.horarioRealIniciado = horarioRealIniciado;
    }

    public Time getHorarioRealEncerrado() {
        return horarioRealEncerrado;
    }

    public void setHorarioRealEncerrado(Time horarioRealEncerrado) {
        this.horarioRealEncerrado = horarioRealEncerrado;
    }
}
