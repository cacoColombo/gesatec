/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.visao;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.cadastro.dao.AtendenteDAO;
import modulo.cadastro.negocio.Atendente;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class AtendenteBusca extends Busca {

    public static AtendenteFormulario form;
    
    /**
     * Creates new form AtendenteBusca
     */
    public AtendenteBusca() {
        this.setName("atendente");
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
                registros = AtendenteDAO.getInstance().findAll(new Atendente());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                Atendente atendente = (Atendente) registros.get(i);
                
                System.out.println(atendente.getId());
                
                modelo.addRow(new Object[]{
                    atendente.getId(), 
                    atendente.getNome()!=null?atendente.getNome():"",
                    atendente.getDataNascimento()!=null?format.format(atendente.getDataNascimento()):"",
                    atendente.getSexo()=='M'?"Masculino":"Feminino",
                    atendente.getEmail()!=null?atendente.getEmail():"",
                    atendente.getCpf()!=null?atendente.getCpf():"",
                    atendente.getRg()!=null?atendente.getRg():"",
                    atendente.getObservacao()!=null?atendente.getObservacao():"",
                    atendente.getTelefoneCelular()!=null?atendente.getTelefoneCelular():"",
                    atendente.getTelefoneResidencial()!=null?atendente.getTelefoneResidencial():"",
                    atendente.getTelefoneTrabalho()!=null?atendente.getTelefoneTrabalho():"",
                    "",
                    atendente.getCep()!=null?atendente.getCep():"",
                    atendente.getBairro()!=null?atendente.getBairro():"",
                    atendente.getEndereco()!=null?atendente.getEndereco():"",
                    atendente.getNumero(),
                    atendente.getComplemento()!=null?atendente.getComplemento():""
                });
                
                // Verifica item a selecionar
                if ( atendente.getId() == selecionar )
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
        form = new AtendenteFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        try {
            int selected = getTabela().getSelectedRow();
            Object registro = getTabela().getValueAt(selected, 0);
            int atendente_id = Integer.parseInt(registro.toString());

            Object atendente = AtendenteDAO.getInstance().getById(new Atendente(), atendente_id);

            form = new AtendenteFormulario(this, true);
            form.popularCampos((Atendente) atendente);
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
            int grupodeatendentes_id = Integer.parseInt(registro.toString());

            int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);

            if ( escolha == JOptionPane.YES_OPTION ) 
            {
                Atendente atendente = new Atendente();
                atendente.setId(grupodeatendentes_id);
                AtendenteDAO.getInstance().remove(atendente);

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

            List<Object> grupos = AtendenteDAO.getInstance().findByCriteria(new Atendente(), Restrictions.conjunction(), or);
            this.atualizarGrid(-1, grupos);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
