/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.negocio;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import modulo.cadastro.negocio.Atendente;
import modulo.cadastro.negocio.Cliente;
import modulo.cadastro.negocio.Profissional;
import modulo.cadastro.negocio.TipoDeAtendimentoDoProfissional;
import modulo.configuracao.negocio.TipoDeAtendimento;

@Entity
@Table(name="agendamento", uniqueConstraints = {
    @UniqueConstraint(columnNames={"dataagendada", "horarioagendado"})
})
public class Agendamento implements Serializable {
    
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
    @JoinColumn(name = "atendente_id", referencedColumnName = "id")
    private Atendente atendente;
    
    @ManyToOne
    @JoinColumn(name = "tipodeatendimentodoprofissional_id", referencedColumnName = "id", nullable = false)
    private TipoDeAtendimentoDoProfissional tipoDeAtendimentoDoProfissional;
    
    @ManyToOne
    @JoinColumn(name = "tipoderepeticao_id", referencedColumnName = "id", nullable = false)
    private TipoDeRepeticao tipoDeRepeticao;
    
    @ManyToOne
    @JoinColumn(name = "statusagendamento_id", referencedColumnName = "id", nullable = false)
    private StatusAgendamento statusAgenda;
    
    @ManyToOne
    @JoinColumn(name = "tipodeatendimento_id", referencedColumnName = "id", nullable = false)
    private TipoDeAtendimento tipoDeAtendimento;
    
    @Column(nullable = false)
    private Date dataRegistro;
    
    @Column(nullable = false)
    private Date dataAgendada;
    
    @Column(nullable = false)
    private Time horarioAgendado;
    
    @Column(nullable = false)
    private Time horarioFimPrevisto;
    
    @Column
    private String observacao;

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

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public TipoDeAtendimentoDoProfissional getTipoDeAtendimentoDoProfissional() {
        return tipoDeAtendimentoDoProfissional;
    }

    public void setTipoDeAtendimentoDoProfissional(TipoDeAtendimentoDoProfissional tipoDeAtendimentoDoProfissional) {
        this.tipoDeAtendimentoDoProfissional = tipoDeAtendimentoDoProfissional;
    }

    public TipoDeRepeticao getTipoDeRepeticao() {
        return tipoDeRepeticao;
    }

    public void setTipoDeRepeticao(TipoDeRepeticao tipoDeRepeticao) {
        this.tipoDeRepeticao = tipoDeRepeticao;
    }

    public StatusAgendamento getStatusAgenda() {
        return statusAgenda;
    }

    public void setStatusAgenda(StatusAgendamento statusAgenda) {
        this.statusAgenda = statusAgenda;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public Time getHorarioAgendado() {
        return horarioAgendado;
    }

    public void setHorarioAgendado(Time horarioAgendado) {
        this.horarioAgendado = horarioAgendado;
    }

    public Time getHorarioFimPrevisto() {
        return horarioFimPrevisto;
    }

    public void setHorarioFimPrevisto(Time horarioFimPrevisto) {
        this.horarioFimPrevisto = horarioFimPrevisto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoDeAtendimento getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }
}
