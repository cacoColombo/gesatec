/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.negocio;

import modulo.administrativo.negocio.UserAccount;

/**
 *
 * @author augusto
 */
public class UsuarioLogado {
    
    private static final UsuarioLogado instance = new UsuarioLogado();

    private UserAccount usuarioLogado;
    
    private UsuarioLogado() {}
    
    public static UsuarioLogado getInstance() 
    {
        return instance;
    }
    
    public void setaUsuarioLogado( UserAccount usuario ) 
    {
        this.usuarioLogado = usuario;
    }
    
    public UserAccount getUsuarioLogado() 
    {
        return usuarioLogado;
    }
    
}
