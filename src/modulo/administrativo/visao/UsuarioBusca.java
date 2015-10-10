/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.visao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.administrativo.dao.UsuarioDAO;
import modulo.administrativo.negocio.UserAccount;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class UsuarioBusca extends Busca {

    public static UsuarioFormulario form;
    
    /**
     * Creates new form UsuarioBusca
     */
    public UsuarioBusca() {
        this.setName("usuario");
        super.initComponents();
        
        this.getBotaoNovo().setEnabled(false);
        this.getBotaoExcluir().setEnabled(false);
        this.setForcarDesabilitarBotaoExcluir(true);
    }
    
    @Override
    public DefaultTableModel construirGrid() {
        
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
                
            // Colunas
            new String [] {
                "ID", 
                "Nome", 
                "Login", 
                "Ativo"
            }
        ) {
            // Tipos
            Class[] types = new Class [] {
                java.lang.Integer.class, 
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };
            
            // Podem ser editados
            boolean[] canEdit = new boolean [] {
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
                registros = UsuarioDAO.getInstance().findAll(new UserAccount());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                UserAccount usuario = (UserAccount) registros.get(i);
                modelo.addRow(new Object[]{
                    usuario.getId(), 
                    usuario.getName(),
                    usuario.getLogin(),
                    usuario.isActive() ? "SIM" : "NÃO"
                });
                
                // Verifica item a selecionar
                if ( usuario.getId() == selecionar )
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
        form = new UsuarioFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int usuario_id = Integer.parseInt(registro.toString());
        
        Object usuario = UsuarioDAO.getInstance().getById(new UserAccount(), usuario_id);
        
        form = new UsuarioFormulario(this, true);
        form.popularCampos((UserAccount) usuario);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodeusuarios_id = Integer.parseInt(registro.toString());
        
        int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( escolha == JOptionPane.YES_OPTION ) 
        {
            UserAccount usuario = new UserAccount();
            usuario.setId(grupodeusuarios_id);
            UsuarioDAO.getInstance().remove(usuario);
            
            this.atualizarGrid(-1, new ArrayList());
            JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void botaoBuscarActionPerformed(ActionEvent evt) {
        String busca = getCampoBusca().getText();
        
        Disjunction or = Restrictions.disjunction();
        or.add(Restrictions.ilike("login", busca, MatchMode.ANYWHERE));
        or.add(Restrictions.ilike("name", busca, MatchMode.ANYWHERE));
        
        try {
            or.add(Restrictions.eq("id", Integer.parseInt(busca)));
        } catch (Exception err) {
        }
        
        List<Object> usuarios = UsuarioDAO.getInstance().findByCriteria(new UserAccount(), Restrictions.conjunction(), or);
        this.atualizarGrid(-1, usuarios);
    }
}
