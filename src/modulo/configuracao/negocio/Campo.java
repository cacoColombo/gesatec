/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.configuracao.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "campo")
public class Campo implements Serializable {
    
    @Transient
    final int CAMPO_TEXTO_SIMPLES = 1;
    
    @Transient
    final int CAMPO_TEXTO_LONGO = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false, length = 45)
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
