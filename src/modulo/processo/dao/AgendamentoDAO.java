/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.dao;

import java.sql.Date;
import java.util.List;
import modulo.sistema.dao.DAO;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author augusto
 */
public class AgendamentoDAO extends DAO {
    
    public List<Object> obterHorariosDoProfissionalParaAgendamento(Date data) {
        Session session = super.getEntityManager().unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT * FROM obterHorariosDoProfissionalParaAgendamento(:data)").setParameter("data", data);
        return query.list();
    }
    
}
