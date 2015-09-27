/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.negocio;

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
@Table(name = "grupodousuario")
public class GrupoDoUsuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UserAccount usuario;
    
    @ManyToOne
    @JoinColumn(name = "grupodeusuarios_id", referencedColumnName = "id", nullable = false)
    private GrupoDeUsuarios grupoDeUsuarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUsuario() {
        return usuario;
    }

    public void setUsuario(UserAccount usuario) {
        this.usuario = usuario;
    }

    public GrupoDeUsuarios getGrupoDeUsuarios() {
        return grupoDeUsuarios;
    }

    public void setGrupoDeUsuarios(GrupoDeUsuarios grupoDeUsuarios) {
        this.grupoDeUsuarios = grupoDeUsuarios;
    }
}
