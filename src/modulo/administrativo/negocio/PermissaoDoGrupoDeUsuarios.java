/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.negocio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "permissaodogrupodeusuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames={"id", "grupodeusuarios_id"})
})
public class PermissaoDoGrupoDeUsuarios implements Serializable {
    
    @Id
    @Column(length = 45, nullable = false)
    private String id; // nome da tela 'name'.
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupodeusuarios_id", referencedColumnName = "id", nullable = false)
    private GrupoDeUsuarios grupoDeUsuarios;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean visualizar;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean inserir;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean atualizar;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean excluir;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean admin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GrupoDeUsuarios getGrupoDeUsuarios() {
        return grupoDeUsuarios;
    }

    public void setGrupoDeUsuarios(GrupoDeUsuarios grupoDeUsuarios) {
        this.grupoDeUsuarios = grupoDeUsuarios;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isInserir() {
        return inserir;
    }

    public void setInserir(boolean inserir) {
        this.inserir = inserir;
    }

    public boolean isAtualizar() {
        return atualizar;
    }

    public void setAtualizar(boolean atualizar) {
        this.atualizar = atualizar;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
