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
import modulo.cadastro.dao.ProfissionalDAO;
import modulo.cadastro.negocio.Profissional;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class ProfissionalBusca extends Busca {

    public static ProfissionalFormulario form;
    
    /**
     * Creates new form ProfissionalBusca
     */
    public ProfissionalBusca() {
        this.setName("profissional");
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
                registros = ProfissionalDAO.getInstance().findAll(new Profissional());
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            
            for ( int i = 0; i < registros.size(); i ++ ) {                
                Profissional profissional = (Profissional) registros.get(i);
                modelo.addRow(new Object[]{
                    profissional.getId(), 
                    profissional.getNome()!=null?profissional.getNome():"",
                    profissional.getDataNascimento()!=null?format.format(profissional.getDataNascimento()):"",
                    profissional.getSexo()=='M'?"Masculino":"Feminino",
                    profissional.getEmail()!=null?profissional.getEmail():"",
                    profissional.getCpf()!=null?profissional.getCpf():"",
                    profissional.getRg()!=null?profissional.getRg():"",
                    profissional.getObservacao()!=null?profissional.getObservacao():"",
                    profissional.getTelefoneCelular()!=null?profissional.getTelefoneCelular():"",
                    profissional.getTelefoneResidencial()!=null?profissional.getTelefoneResidencial():"",
                    profissional.getTelefoneTrabalho()!=null?profissional.getTelefoneTrabalho():"",
                    "",
                    profissional.getCep()!=null?profissional.getCep():"",
                    profissional.getBairro()!=null?profissional.getBairro():"",
                    profissional.getEndereco()!=null?profissional.getEndereco():"",
                    profissional.getNumero(),
                    profissional.getComplemento()!=null?profissional.getComplemento():""
                });
                
                // Verifica item a selecionar
                if ( profissional.getId() == selecionar )
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
        form = new ProfissionalFormulario(this, true);
        form.populaCertificacoes(null);
        form.populaEspecializacoes(null);
        form.populaPadroesDeAtendimento(null);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }

    @Override
    public void botaoEditarActionPerformed(ActionEvent evt) {
        try {
            int selected = getTabela().getSelectedRow();
            Object registro = getTabela().getValueAt(selected, 0);
            int profissional_id = Integer.parseInt(registro.toString());

            Object profissional = ProfissionalDAO.getInstance().getById(new Profissional(), profissional_id);

            form = new ProfissionalFormulario(this, true);
            form.popularCampos((Profissional) profissional);
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
            int grupodeprofissionals_id = Integer.parseInt(registro.toString());

            int escolha = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja excluir este registro?", "Atenção!", JOptionPane.YES_NO_OPTION);

            if ( escolha == JOptionPane.YES_OPTION ) 
            {
                Profissional profissional = new Profissional();
                profissional.setId(grupodeprofissionals_id);
                ProfissionalDAO.getInstance().remove(profissional);

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

            List<Object> grupos = ProfissionalDAO.getInstance().findByCriteria(new Profissional(), Restrictions.conjunction(), or);
            this.atualizarGrid(-1, grupos);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
