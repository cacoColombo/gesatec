/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.negocio;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import modulo.sistema.dao.DAOAuditoria;

@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(nullable = false)
    private Date horario;
    
    @Column(nullable = false)
    private String usuario;
   
    @Column(nullable = false)
    private String message;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the horario
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    public static void registra(String message){
        Auditoria auditoria = new Auditoria();
        java.util.Date agora = new java.util.Date();
        auditoria.setHorario(new Date(agora.getTime()));
        auditoria.setUsuario(UsuarioLogado.getInstance().getUsuarioLogado().getName()+"("+UsuarioLogado.getInstance().getUsuarioLogado().getLogin()+")");
        auditoria.setMessage(message);
        DAOAuditoria.getInstance().merge(auditoria);
    }
}
