/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.negocio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import modulo.cadastro.negocio.Pessoa;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Pessoa pessoa;
    
    @Column(nullable = false, length = 45)
    private String login;
    
    @Column(nullable = false, length = 45)
    private String senha;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    private boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
