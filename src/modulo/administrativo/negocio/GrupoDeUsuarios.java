/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grupodeusuarios")
public class GrupoDeUsuarios implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "grupoDeUsuarios", targetEntity = PermissaoDoGrupoDeUsuarios.class, cascade = CascadeType.REMOVE)
    private List<PermissaoDoGrupoDeUsuarios> permissaoDoGrupoDeUsuarios;

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

    public List<PermissaoDoGrupoDeUsuarios> getPermissaoDoGrupoDeUsuarios() {
        return permissaoDoGrupoDeUsuarios;
    }

    public void setPermissaoDoGrupoDeUsuarios(List<PermissaoDoGrupoDeUsuarios> permissaoDoGrupoDeUsuarios) {
        this.permissaoDoGrupoDeUsuarios = permissaoDoGrupoDeUsuarios;
    }
}
