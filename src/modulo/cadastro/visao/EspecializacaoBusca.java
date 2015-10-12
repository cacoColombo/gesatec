/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.visao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.cadastro.dao.EspecializacaoDAO;
import modulo.cadastro.negocio.Especializacao;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class EspecializacaoBusca extends Busca {

    public static EspecializacaoFormulario form;
    
    /**
     * Creates new form EspecializacaoBusca
     */
    public EspecializacaoBusca() {
        this.setName("especializacao");
        super.initComponents();
    }
    
    @Override
    public DefaultTableModel construirGrid() {
        
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
                
            // Colunas
            new String [] {
                "ID", 
                "Nome"
            }
        ) {
            // Tipos
            Class[] types = new Class [] {
                java.lang.Integer.class, 
                java.lang.String.class
            };
            
            // Podem ser editados
            boolean[] canEdit = new boolean [] {
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
                registros = EspecializacaoDAO.getInstance().findAll(new Especializacao());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                Especializacao especializacao = (Especializacao) registros.get(i);
                modelo.addRow(new Object[]{
                    especializacao.getId(), 
                    especializacao.getNome()!=null?especializacao.getNome():"",
                });
                
                // Verifica item a selecionar
                if ( especializacao.getId() == selecionar )
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
        form = new EspecializacaoFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        try {
            int selected = getTabela().getSelectedRow();
            Object registro = getTabela().getValueAt(selected, 0);
            int especializacao_id = Integer.parseInt(registro.toString());

            Object especializacao = EspecializacaoDAO.getInstance().getById(new Especializacao(), especializacao_id);

            form = new EspecializacaoFormulario(this, true);
            form.popularCampos((Especializacao) especializacao);
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
            int grupodeespecializacaos_id = Integer.parseInt(registro.toString());

            int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);

            if ( escolha == JOptionPane.YES_OPTION ) 
            {
                Especializacao especializacao = new Especializacao();
                especializacao.setId(grupodeespecializacaos_id);
                EspecializacaoDAO.getInstance().remove(especializacao);

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

            List<Object> grupos = EspecializacaoDAO.getInstance().findByCriteria(new Especializacao(), Restrictions.conjunction(), or);
            this.atualizarGrid(-1, grupos);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
