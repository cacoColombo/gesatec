/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import modulo.sistema.dao.DAO;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author augusto
 */
public class AgendamentoDAO extends DAO {
    
    public List<Object> obterHorariosParaAgendamento(Date data, int profissionalId, int tipoDeAtendimentoId) {
        Session session = super.getEntityManager().unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT * FROM obterHorariosParaAgendamento(:data, :profissional_id, :tipodeatendimento_id)")
                             .setParameter("data", data)
                             .setParameter("profissional_id", profissionalId)
                             .setParameter("tipodeatendimento_id", tipoDeAtendimentoId);
        
        return query.list();
    }
    
    
    public List<Object> obterHorariosParaAgendamento(Date data, int profissionalId, int tipoDeAtendimentoId, int agendamentoId, Time horario) {
        Session session = super.getEntityManager().unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT * FROM obterHorariosParaAgendamento(:data, :profissional_id, :tipodeatendimento_id, :agendamento_id, :horario) WHERE esta_disponivel = TRUE")
                             .setParameter("data", data)
                             .setParameter("profissional_id", profissionalId)
                             .setParameter("tipodeatendimento_id", tipoDeAtendimentoId)
                             .setParameter("agendamento_id", agendamentoId)
                             .setParameter("horario", horario);
        
        return query.list();
    }
    
}
