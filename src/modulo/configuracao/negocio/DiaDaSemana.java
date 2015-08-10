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
@Table(name = "diadasemana")
public class DiaDaSemana implements Serializable {
    
    @Transient
    final int DIA_DA_SEMANA_DOMINGO = 1;
    
    @Transient
    final int DIA_DA_SEMANA_SEGUNDA_FEIRA = 2;
    
    @Transient
    final int DIA_DA_SEMANA_TERCA_FEIRA = 3;
    
    @Transient
    final int DIA_DA_SEMANA_QUARTA_FEIRA = 4;
    
    @Transient
    final int DIA_DA_SEMANA_QUINTA_FEIRA = 5;
    
    @Transient
    final int DIA_DA_SEMANA_SEXTA_FEIRA = 6;
    
    @Transient
    final int DIA_DA_SEMANA_SABADO = 7;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false, length = 45)
    private String abreviatura;

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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
