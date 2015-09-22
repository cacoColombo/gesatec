/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.configuracao.visao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.configuracao.dao.PadraoDeAtendimentoDAO;
import modulo.configuracao.negocio.PadraoDeAtendimento;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class PadraoDeAtendimentoBusca extends Busca {

    public static PadraoDeAtendimentoFormulario form;
    
    /**
     * Creates new form PadraoDeAtendimentoBusca
     */
    public PadraoDeAtendimentoBusca() {
        super.initComponents();
    }
    
    @Override
    public DefaultTableModel construirGrid() {
        
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
                
            // Colunas
            new String [] {
                "ID", 
                "Nome",
                "Dia da Semana",
                "Horário Início Expediente",
                "Horário Fim Expediente",
                "Tempo Médio Consulta"
            }
        ) {
            // Tipos
            Class[] types = new Class [] {
                java.lang.Integer.class, 
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };
            
            // Podem ser editados
            boolean[] canEdit = new boolean [] {
                false, 
                false, 
                false, 
                false, 
                false, 
                false
            };
        };
        
        return defaultTableModel;
    }

    @Override
    public void atualizarGrid(int selecionar, List<Object> registros) {
        try {           
            
            if ( registros.isEmpty() )
            {
                registros = PadraoDeAtendimentoDAO.getInstance().findAll(new PadraoDeAtendimento());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                PadraoDeAtendimento padraoDeAtendimento = (PadraoDeAtendimento) registros.get(i);
                modelo.addRow(new Object[]{
                    padraoDeAtendimento.getId(), 
                    padraoDeAtendimento.getNome()!=null?padraoDeAtendimento.getNome():"",
                    padraoDeAtendimento.getDiaDaSemana().toString(),
                    padraoDeAtendimento.getHorarioInicioExpediente().toString(),
                    padraoDeAtendimento.getHorarioFimExpediente().toString(),
                    padraoDeAtendimento.getTempoMedioConsulta() + ""
                });
                
                // Verifica item a selecionar
                if ( padraoDeAtendimento.getId() == selecionar )
                {
                    getTabela().addRowSelectionInterval(i, i);
                }
            }
            
            if ( selecionar == -1 )
            {
                getBotaoEditar().setEnabled(false);
                getBotaoExcluir().setEnabled(false);
            }
            
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar grid: " + err.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void botaoNovoActionPerformed(ActionEvent evt) {
        form = new PadraoDeAtendimentoFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int padraoDeAtendimento_id = Integer.parseInt(registro.toString());
        
        Object padraoDeAtendimento = PadraoDeAtendimentoDAO.getInstance().getById(new PadraoDeAtendimento(), padraoDeAtendimento_id);
        
        form = new PadraoDeAtendimentoFormulario(this, true);
        form.popularCampos((PadraoDeAtendimento) padraoDeAtendimento);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodepadraoDeAtendimentos_id = Integer.parseInt(registro.toString());
        
        int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( escolha == JOptionPane.YES_OPTION ) 
        {
            PadraoDeAtendimento padraoDeAtendimento = new PadraoDeAtendimento();
            padraoDeAtendimento.setId(grupodepadraoDeAtendimentos_id);
            PadraoDeAtendimentoDAO.getInstance().remove(padraoDeAtendimento);
            
            this.atualizarGrid(-1, new ArrayList());
            JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void botaoBuscarActionPerformed(ActionEvent evt) {
        String busca = getCampoBusca().getText();
        
        Disjunction or = Restrictions.disjunction();
        or.add(Restrictions.ilike("nome", busca, MatchMode.ANYWHERE));
        //or.add(Restrictions.ilike("ativo", busca, MatchMode.ANYWHERE));
        
        try {
            or.add(Restrictions.eq("id", Integer.parseInt(busca)));
        } catch (Exception err) {
        }
        
        List<Object> grupos = PadraoDeAtendimentoDAO.getInstance().findByCriteria(new PadraoDeAtendimento(), Restrictions.conjunction(), or);
        this.atualizarGrid(-1, grupos);
    }
}
