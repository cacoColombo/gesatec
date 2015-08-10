/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Tipoderepeticao")
public class TipoDeRepeticao implements Serializable {
    
    @Transient
    final int TIPO_DE_REPETICAO_DIARIA = 1;
    
    @Transient
    final int TIPO_DE_REPETICAO_SEMANAL = 2;
    
    @Transient
    final int TIPO_DE_REPETICAO_MENSAL = 3;
    
    @Transient
    final int TIPO_DE_REPETICAO_ANUAL = 4;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(unique = true, nullable = false)
    private String nome;

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
}
