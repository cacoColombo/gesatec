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
import modulo.cadastro.dao.CertificacaoDAO;
import modulo.cadastro.negocio.Certificacao;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class CertificacaoBusca extends Busca {

    public static CertificacaoFormulario form;
    
    /**
     * Creates new form CertificacaoBusca
     */
    public CertificacaoBusca() {
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
                registros = CertificacaoDAO.getInstance().findAll(new Certificacao());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                Certificacao certificacao = (Certificacao) registros.get(i);
                modelo.addRow(new Object[]{
                    certificacao.getId(), 
                    certificacao.getNome()!=null?certificacao.getNome():"",
                });
                
                // Verifica item a selecionar
                if ( certificacao.getId() == selecionar )
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
        form = new CertificacaoFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int certificacao_id = Integer.parseInt(registro.toString());
        
        Object certificacao = CertificacaoDAO.getInstance().getById(new Certificacao(), certificacao_id);
        
        form = new CertificacaoFormulario(this, true);
        form.popularCampos((Certificacao) certificacao);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodecertificacaos_id = Integer.parseInt(registro.toString());
        
        int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( escolha == JOptionPane.YES_OPTION ) 
        {
            Certificacao certificacao = new Certificacao();
            certificacao.setId(grupodecertificacaos_id);
            CertificacaoDAO.getInstance().remove(certificacao);
            
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
        
        List<Object> grupos = CertificacaoDAO.getInstance().findByCriteria(new Certificacao(), Restrictions.conjunction(), or);
        this.atualizarGrid(-1, grupos);
    }
}
