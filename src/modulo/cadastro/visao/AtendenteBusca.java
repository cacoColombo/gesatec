/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.visao;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modulo.cadastro.dao.AtendenteDAO;
import modulo.cadastro.negocio.Atendente;

/**
 *
 * @author augusto
 */
public class AtendenteBusca extends javax.swing.JInternalFrame {

    public static AtendenteFormulario form;
    
    /**
     * Creates new form ModeloBusca
     */
    public AtendenteBusca() {
        initComponents();
        
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(tabela.getSelectedRow() > tabela.getRowCount()){
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                }
                else{
                    botaoExcluir.setEnabled(true);
                    botaoEditar.setEnabled(true);
                }
            }
        });
        
        setTitle("Atendente");
        this.setBorder(null);
        this.desabilitaAcoesDeEdicaoEExclusao();
        tabela.setSelectionBackground(new java.awt.Color(22, 160, 133)); 
        
        this.atualizarGrid(-1);

        botaoNovo.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/novo.png")));
        botaoEditar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/editar.png")));
        botaoExcluir.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/excluir.png")));
        botaoAtualizar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/atualizar.png")));
        botaoBuscar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/buscar.png")));
    }
    
    /**
     * Recebe o id do registro que deverá ser selecionado automáticamente.
     * Se receber -1, não selecionará registro algum.
     * 
     * @param selecionar 
     */
    public final void atualizarGrid(int selecionar) {
        try {
            
            List<Object> certificacoes = AtendenteDAO.getInstance().findAll(new Atendente());
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < certificacoes.size(); i ++ ) {
                Atendente atendente = (Atendente) certificacoes.get(i);
                modelo.addRow(new Object[]{
                    atendente.getId(), 
                    atendente.getNome()
                });
                
                // Verifica item a selecionar
                if ( atendente.getId() == selecionar )
                {
                    tabela.addRowSelectionInterval(i, i);
                }
            }
            
            if ( selecionar == -1 )
            {
                botaoEditar.setEnabled(false);
                botaoExcluir.setEnabled(false);
            }
            
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar grid: " + err.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void abilitaAcoesDeEdicaoEExclusao() {
        botaoEditar.setEnabled(true);
        botaoExcluir.setEnabled(true);
    }

    private void desabilitaAcoesDeEdicaoEExclusao() {
        botaoEditar.setEnabled(false);
        botaoExcluir.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        botaoNovo = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        campoBusca = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setTitle("Atendente");

        toolbar.setRollover(true);

        botaoNovo.setText("Novo");
        botaoNovo.setFocusable(false);
        botaoNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });
        toolbar.add(botaoNovo);

        botaoEditar.setText("Editar");
        botaoEditar.setFocusable(false);
        botaoEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });
        toolbar.add(botaoEditar);

        botaoExcluir.setText("Excluir");
        botaoExcluir.setFocusable(false);
        botaoExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(botaoExcluir);

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.setFocusable(false);
        botaoAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(botaoAtualizar);

        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Data de nascimento", "Sexo", "E-mail", "CPF", "RG", "Observação", "Telefone celular", "Telefone residencial", "Telefone de trabalho", "Cidade", "Cep", "Bairro", "Endereço", "Numero", "Complemento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        form = new AtendenteFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        
        /**
        List<Atendente> atendentes = AtendenteDAO.getInstance().find(campoBusca.getText());
        DefaultTableModel model = new DefaultTableModel(new Object[]
        {"ID","Nome","Data Nascimento","Sexo","Email","CPF","RG","Obs.","Tel. Celular", "Tel. Residencial", "Tel. Trabalho", "Cidade", "CEP", "Bairro", "Endereço", "Número", "Complemento"}, 0);
        for(Pessoa a : atendentes){
            model.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getDataNascimento().toString(),
                a.getSexo()=='M'?"Masculino":"Feminino",
                a.getEmail(),
                a.getCpf(),
                a.getRg(),
                a.getObservacao(),
                a.getTelefoneCelular(),
                a.getTelefoneResidencial(),
                a.getTelefoneTrabalho(),
                a.getCidade()!=null?"Hey!":"Oops!",
                a.getCep(),
                a.getBairro(),
                a.getEndereco(),
                a.getNumero(),
                a.getComplemento()
            });
        }
        tabela.setModel(model);
        */
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        int selected = tabela.getSelectedRow();
        Object registro = tabela.getValueAt(selected, 0);
        int atendente_id = Integer.parseInt(registro.toString());
        
        Object atendente = AtendenteDAO.getInstance().getById(new Atendente(), atendente_id);
        
        form = new AtendenteFormulario(this, true);
        form.popularCampos((Atendente) atendente);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        
        
        
        /**
        form = new AtendenteFormulario(this, true);
        form.setLocationRelativeTo(null);
        form.setObejtoEditado(Long.parseLong(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
        form.setVisible(true);
        */
        
    }//GEN-LAST:event_botaoEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
