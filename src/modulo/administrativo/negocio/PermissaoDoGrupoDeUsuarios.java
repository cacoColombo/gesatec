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
@Table(name = "permissaodogrupodeusuarios")
public class PermissaoDoGrupoDeUsuarios implements Serializable {
    
    final char PERMISSAO_INSERIR = 'I';
    final char PERMISSAO_ATUALIZAR = 'A';
    final char PERMISSAO_EXCLUIR = 'E';
    final char PERMISSAO_LISTAR = 'L';
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "grupodeusuarios_id", referencedColumnName = "id", nullable = false)
    private GrupoDeUsuarios grupoDeUsuarios;
    
    @Column(nullable = false, length = 45)
    private String tela; // ex.: Cliente, Agenda...
    
    @Column(length = 45)
    private String permissoes; // ex. 'I;U;D;L'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GrupoDeUsuarios getGrupoDeUsuarios() {
        return grupoDeUsuarios;
    }

    public void setGrupoDeUsuarios(GrupoDeUsuarios grupoDeUsuarios) {
        this.grupoDeUsuarios = grupoDeUsuarios;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }
}
