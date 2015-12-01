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
import modulo.configuracao.negocio.TipoDeAtendimento;
import modulo.sistema.dao.DAO;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class TipoDeAtendimentoBusca extends Busca {

    public static TipoDeAtendimentoFormulario form;
    
    /**
     * Creates new form PadraoDeAtendimentoBusca
     */
    public TipoDeAtendimentoBusca() {
        this.setName("tipodeatendimento");
        super.initComponents();
        this.setTitle("Tipo de atendimento");
    }
    
    @Override
    public DefaultTableModel construirGrid() {
        
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
                
            // Colunas
            new String [] {
                "ID", 
                "Nome",
                "Ativo"
            }
        ) {
            // Tipos
            Class[] types = new Class [] {
                java.lang.Integer.class, 
                java.lang.String.class,
                java.lang.Boolean.class
            };
            
            // Podem ser editados
            boolean[] canEdit = new boolean [] {
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
                registros = DAO.getInstance().findAll(new TipoDeAtendimento());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                TipoDeAtendimento tipoDeAtendimento = (TipoDeAtendimento) registros.get(i);
                modelo.addRow(new Object[]{
                    tipoDeAtendimento.getId(), 
                    tipoDeAtendimento.getNome()!=null?tipoDeAtendimento.getNome():"",
                    tipoDeAtendimento.isAtivo()?"true":"false"
                });
                
                // Verifica item a selecionar
                if ( tipoDeAtendimento.getId() == selecionar )
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
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void botaoNovoActionPerformed(ActionEvent evt) {
        form = new TipoDeAtendimentoFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        try {
            int selected = getTabela().getSelectedRow();
            Object registro = getTabela().getValueAt(selected, 0);
            int tipoDeAtendimento_id = Integer.parseInt(registro.toString());

            Object tipoDeAtendimento = DAO.getInstance().getById(new TipoDeAtendimento(), tipoDeAtendimento_id);

            form = new TipoDeAtendimentoFormulario(this, true);
            form.popularCampos((TipoDeAtendimento) tipoDeAtendimento);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        try {
            int selected = getTabela().getSelectedRow();
            Object registro = getTabela().getValueAt(selected, 0);
            int grupodetipoDeAtendimentos_id = Integer.parseInt(registro.toString());

            int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);

            if ( escolha == JOptionPane.YES_OPTION ) 
            {
                TipoDeAtendimento tipoDeAtendimento = new TipoDeAtendimento();
                tipoDeAtendimento.setId(grupodetipoDeAtendimentos_id);
                DAO.getInstance().remove(tipoDeAtendimento);

                this.atualizarGrid(-1, new ArrayList());
                JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void botaoBuscarActionPerformed(ActionEvent evt) {
        try {
            String busca = getCampoBusca().getText();

            Disjunction or = Restrictions.disjunction();
            or.add(Restrictions.ilike("nome", busca, MatchMode.ANYWHERE));
            //or.add(Restrictions.ilike("ativo", busca, MatchMode.ANYWHERE));

            try {
                or.add(Restrictions.eq("id", Integer.parseInt(busca)));
            } catch (Exception err) {
            }

            List<Object> grupos = DAO.getInstance().findByCriteria(new TipoDeAtendimento(), Restrictions.conjunction(), or);
            this.atualizarGrid(-1, grupos);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
