/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.negocio;

import java.util.LinkedList;
import java.util.List;
import modulo.administrativo.negocio.PermissaoDoGrupoDeUsuarios;
import modulo.administrativo.negocio.UserAccount;

/**
 *
 * @author augusto
 */
public class UsuarioLogado {
    
    private static final UsuarioLogado instance = new UsuarioLogado();

    private UserAccount usuarioLogado;
    
    private List<Object> gruposDoUsuarioLogado;
    
    private LinkedList<Object> permissoesDosGruposDoUsuarioLogado = new LinkedList<>();
    
    private UsuarioLogado() {}
    
    public static UsuarioLogado getInstance() {
        return instance;
    }
    
    public void setaUsuarioLogado( UserAccount usuario ) {
        this.usuarioLogado = usuario;
    }
    
    public UserAccount getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Object> getGruposDoUsuarioLogado() {
        return gruposDoUsuarioLogado;
    }

    public void setGruposDoUsuarioLogado(List<Object> gruposDoUsuarioLogado) {
        this.gruposDoUsuarioLogado = gruposDoUsuarioLogado;
    }

    public LinkedList<Object> getPermissoesDosGruposDoUsuarioLogado() {
        return permissoesDosGruposDoUsuarioLogado;
    }

    public void setPermissoesDosGruposDoUsuarioLogado(LinkedList<Object> permissoesDosGruposDoUsuarioLogado) {
        this.permissoesDosGruposDoUsuarioLogado = permissoesDosGruposDoUsuarioLogado;
    }
}
