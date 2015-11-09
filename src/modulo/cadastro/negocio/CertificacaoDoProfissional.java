/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.negocio;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="certificacaodoprofissional", uniqueConstraints = {
    @UniqueConstraint(columnNames={"profissional_id", "certificacao_id"})
})
public class CertificacaoDoProfissional implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name = "profissional_id", referencedColumnName = "id", nullable = false)
    private Profissional profissional;
    
    @ManyToOne
    @JoinColumn(name = "certificacao_id", referencedColumnName = "id", nullable = false)
    private Certificacao certificacao;
    
    @Column(nullable = false)
    private String registro;
    
    @Column
    private Date dataExpedicao;
    
    @Column
    private String orgaoExpedidor;

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

    public Certificacao getCertificacao() {
        return certificacao;
    }

    public void setCertificacao(Certificacao certificacao) {
        this.certificacao = certificacao;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Date getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(Date dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }
}
