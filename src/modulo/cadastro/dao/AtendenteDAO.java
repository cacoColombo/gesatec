/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modulo.cadastro.negocio.Pessoa;
import modulo.cadastro.negocio.Atendente;

/**
 *
 * @author Caco
 */
public class AtendenteDAO 
{
    private static AtendenteDAO instance;
    protected EntityManager entityManager;

    public AtendenteDAO() 
    {
        entityManager = getEntityManager();
    }
    
    public static AtendenteDAO getInstance() 
    {
        if (instance == null) 
        {
            instance = new AtendenteDAO();
        }
        
        return instance;
    }

    private EntityManager getEntityManager() 
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        
        if (entityManager == null) 
        {
            entityManager = factory.createEntityManager();
        }
        
        return entityManager;
    }

    public Atendente getById(Atendente object, final long id) 
    {
        return entityManager.find(object.getClass(), id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Atendente> find(String name) 
    {
        return entityManager.createQuery("from Atendente where nome like '%" + name + "%'").getResultList();
       // return entityManager.createQuery("FROM atendente a INNER JOIN pessoa p ON (a.id = p.id) where nome like '%" + name + "%'").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Atendente> findAll(Atendente object) 
    {
        return entityManager.createQuery("FROM " + object.getClass().getName()).getResultList();
    }

    public void persist(Atendente object) 
    {
        try 
        {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Atendente object) 
    {
        try 
        {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.detach(object);
            entityManager.getTransaction().commit();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Atendente object) 
    {
        try 
        {
            entityManager.getTransaction().begin();
            object = entityManager.find(object.getClass(), object.getClass().getMethod("getId").invoke(object));
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(Atendente object, final int id) 
    {
        try 
        {
            Atendente objectRm = getById(object, id);            
            remove(objectRm);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}


