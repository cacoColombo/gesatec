/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.visao;

import modulo.administrativo.visao.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.cadastro.dao.ClienteDAO;
import modulo.cadastro.negocio.Cliente;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class ClienteBusca extends Busca {

    public static ClienteFormulario form;
    
    /**
     * Creates new form ClienteBusca
     */
    public ClienteBusca() {
        this.setName("cliente");
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
                "Data Nascimento", 
                "Sexo",
                "E-mail",
                "CPF",
                "RG",
                "Obs.",
                "Tel. Celular",
                "Tel. Residencial",
                "Tel. Trabalho",
                "Cidade",
                "CEP",
                "Bairro",
                "Endereço",
                "Número",
                "Complemento"
            }
        ) {
            // Tipos
            Class[] types = new Class [] {
                java.lang.Integer.class, 
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
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
                false,
                false,
                false,
                false,
                false,
                false,
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
                registros = ClienteDAO.getInstance().findAll(new Cliente());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                Cliente cliente = (Cliente) registros.get(i);
                modelo.addRow(new Object[]{
                    cliente.getId(), 
                    cliente.getNome()!=null?cliente.getNome():"",
                    cliente.getDataNascimento()!=null?format.format(cliente.getDataNascimento()):"",
                    cliente.getSexo()=='M'?"Masculino":"Feminino",
                    cliente.getEmail()!=null?cliente.getEmail():"",
                    cliente.getCpf()!=null?cliente.getCpf():"",
                    cliente.getRg()!=null?cliente.getRg():"",
                    cliente.getObservacao()!=null?cliente.getObservacao():"",
                    cliente.getTelefoneCelular()!=null?cliente.getTelefoneCelular():"",
                    cliente.getTelefoneResidencial()!=null?cliente.getTelefoneResidencial():"",
                    cliente.getTelefoneTrabalho()!=null?cliente.getTelefoneTrabalho():"",
                    "",
                    cliente.getCep()!=null?cliente.getCep():"",
                    cliente.getBairro()!=null?cliente.getBairro():"",
                    cliente.getEndereco()!=null?cliente.getEndereco():"",
                    cliente.getNumero(),
                    cliente.getComplemento()!=null?cliente.getComplemento():""
                });
                
                // Verifica item a selecionar
                if ( cliente.getId() == selecionar )
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
        form = new ClienteFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int cliente_id = Integer.parseInt(registro.toString());
        
        Object cliente = ClienteDAO.getInstance().getById(new Cliente(), cliente_id);
        
        form = new ClienteFormulario(this, true);
        form.popularCampos((Cliente) cliente);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoExcluirActionPerformed(ActionEvent evt) {
        int selected = getTabela().getSelectedRow();
        Object registro = getTabela().getValueAt(selected, 0);
        int grupodeclientes_id = Integer.parseInt(registro.toString());
        
        int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( escolha == JOptionPane.YES_OPTION ) 
        {
            Cliente cliente = new Cliente();
            cliente.setId(grupodeclientes_id);
            ClienteDAO.getInstance().remove(cliente);
            
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
        
        List<Object> grupos = ClienteDAO.getInstance().findByCriteria(new Cliente(), Restrictions.conjunction(), or);
        this.atualizarGrid(-1, grupos);
    }
}
