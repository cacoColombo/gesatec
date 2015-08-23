/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modulo.administrativo.negocio.GrupoDeUsuarios;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class DAO {

    private static DAO instance;
    protected EntityManager entityManager;

    public DAO() {
        entityManager = getEntityManager();
    }

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }

        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");

        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Object getById(Object object, final int id) {
        return entityManager.find(object.getClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<Object> findAll(Object object) {
        return entityManager.createQuery("FROM " + object.getClass().getName()).getResultList();
    }
    
    public List<Object> findByCriteria(Object object, Criterion[] criterions) {
        Session session = this.getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(object.getClass().getName());
        
        for (int c = 0; c < criterions.length; c ++) {
            criteria.add(criterions[c]);
        }
        
        return criteria.list();
    }

    public void persist(Object object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Object object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Object object) {
        try {
            entityManager.getTransaction().begin();
            object = entityManager.find(object.getClass(), object.getClass().getMethod("getId").invoke(object));
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(Object object, final int id) {
        try {
            Object objectRm = getById(object, id);
            remove(objectRm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
