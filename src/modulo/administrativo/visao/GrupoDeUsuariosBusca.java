/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.visao;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modulo.administrativo.dao.GrupoDeUsuariosDAO;
import modulo.administrativo.negocio.GrupoDeUsuarios;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class GrupoDeUsuariosBusca extends Busca {

    public static GrupoDeUsuariosFormulario form;
    
    /**
     * Creates new form ModeloBusca
     */
    public GrupoDeUsuariosBusca() {
        this.setName("grupodeusuarios");
        initComponents();
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
                java.lang.String.class,
            };
            
            // Podem ser editados
            boolean[] canEdit = new boolean [] {
                false, 
                false,
            };
        };
        
        return defaultTableModel;
    }
    
    /**
     * Recebe o id do registro que deverá ser selecionado automáticamente.
     * Se receber -1, não selecionará registro algum.
     * 
     * @param selecionar 
     */
    public final void atualizarGrid(int selecionar, List<Object> grupoDeUsuarios) {
        try {           
            
            if ( grupoDeUsuarios.isEmpty() )
            {
                grupoDeUsuarios = GrupoDeUsuariosDAO.getInstance().findAll(new GrupoDeUsuarios());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < grupoDeUsuarios.size(); i ++ ) {                
                GrupoDeUsuarios gruposDeUsuarios = (GrupoDeUsuarios) grupoDeUsuarios.get(i);
                modelo.addRow(new Object[]{gruposDeUsuarios.getId(), gruposDeUsuarios.getNome()});
                
                // Verifica item a selecionar
                if ( gruposDeUsuarios.getId() == selecionar )
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
    public void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        form = new GrupoDeUsuariosFormulario(this, true);
        form.atualizarGridPermissoes();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_botaoNovoActionPerformed

    @Override
    public void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodeusuarios_id = Integer.parseInt(registro.toString());
        
        Object grupoDeUsuarios = GrupoDeUsuariosDAO.getInstance().getById(new GrupoDeUsuarios(), grupodeusuarios_id);
        
        form = new GrupoDeUsuariosFormulario(this, true);
        form.popularCampos((GrupoDeUsuarios) grupoDeUsuarios);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_botaoEditarActionPerformed

    @Override
    public void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodeusuarios_id = Integer.parseInt(registro.toString());
        
        int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( escolha == JOptionPane.YES_OPTION ) 
        {
            GrupoDeUsuarios grupoDeUsuarios = new GrupoDeUsuarios();
            grupoDeUsuarios.setId(grupodeusuarios_id);
            GrupoDeUsuariosDAO.getInstance().remove(grupoDeUsuarios);
            
            this.atualizarGrid(-1, new ArrayList());
            JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    @Override
    public void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        String busca = getCampoBusca().getText();
        
        Disjunction or = Restrictions.disjunction();
        or.add(Restrictions.ilike("nome", busca, MatchMode.ANYWHERE));
        
        try {
            or.add(Restrictions.eq("id", Integer.parseInt(busca)));
        } catch (Exception err) {
        }
        
        List<Object> grupos = GrupoDeUsuariosDAO.getInstance().findByCriteria(new GrupoDeUsuarios(), Restrictions.conjunction(), or);
        this.atualizarGrid(-1, grupos);
    }//GEN-LAST:event_botaoBuscarActionPerformed
  
}
