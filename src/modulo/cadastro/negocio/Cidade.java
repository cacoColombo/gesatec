/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private int ibge_id;
    
    @OneToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
    private Estado estado_id;
    
    @Column
    private int populacao_2010;
    
    @Column
    private float densidade_demo;
    
    @Column
    private String gentilico;
    
    @Column
    private float area;

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

    public int getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(int ibge_id) {
        this.ibge_id = ibge_id;
    }

    /**
     * @return the estado_id
     */
    public Estado getEstadoId() {
        return estado_id;
    }

    /**
     * @param estado_id the estado_id to set
     */
    public void setEstadoId(Estado estado_id) {
        this.estado_id = estado_id;
    }

    /**
     * @return the populacao_2010
     */
    public int getPopulacao_2010() {
        return populacao_2010;
    }

    /**
     * @param populacao_2010 the populacao_2010 to set
     */
    public void setPopulacao_2010(int populacao_2010) {
        this.populacao_2010 = populacao_2010;
    }

    /**
     * @return the densidade_demo
     */
    public float getDensidade_demo() {
        return densidade_demo;
    }

    /**
     * @param densidade_demo the densidade_demo to set
     */
    public void setDensidade_demo(float densidade_demo) {
        this.densidade_demo = densidade_demo;
    }

    /**
     * @return the gentilico
     */
    public String getGentilico() {
        return gentilico;
    }

    /**
     * @param gentilico the gentilico to set
     */
    public void setGentilico(String gentilico) {
        this.gentilico = gentilico;
    }

    /**
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(float area) {
        this.area = area;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
