/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.visao;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import modulo.sistema.visao.*;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.administrativo.dao.GrupoDeUsuariosDAO;
import modulo.administrativo.dao.PermissaoDoGrupoDeUsuariosDAO;
import modulo.administrativo.negocio.GrupoDeUsuarios;
import modulo.administrativo.negocio.PermissaoDoGrupoDeUsuarios;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public final class GrupoDeUsuariosFormulario extends javax.swing.JDialog {

    public static GrupoDeUsuariosBusca parent;
    
    /**
     * Creates new form AtendenteFormulario
     */
    public GrupoDeUsuariosFormulario(GrupoDeUsuariosBusca parent, boolean modal) {
        this.parent = parent;
        this.setModal(modal);
        this.setLocation(600, 530);
        initComponents();
        
        botaoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/salvar.png")));
        botaoCancelar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cancelar.png")));
        
        tabelaPermissoes.setSelectionBackground(new java.awt.Color(22, 160, 133));
    }
    
    public void popularCampos(GrupoDeUsuarios grupoDeUsuarios) {
        id.setText(Integer.toString(grupoDeUsuarios.getId()));
        nome.setText(grupoDeUsuarios.getNome());
        this.atualizarGridPermissoes();
    }
    
    public boolean validarCampos() {
        try {
            if ( !(nome.getText().length() > 0) ) {
                throw new Exception("O campo 'Nome' é requerido!");
            }
            
            return true;
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this, err.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void atualizarGridPermissoes()
    {
        try{
            
            List<Object> permissoes = new ArrayList();
            
            if ( id.getText().length() > 0 ) {
                GrupoDeUsuarios grupoDeusuarios = new GrupoDeUsuarios();
                grupoDeusuarios.setId(Integer.parseInt(id.getText()));

                Criterion[] criterions = {Restrictions.eq("grupoDeUsuarios", grupoDeusuarios)};
                permissoes = PermissaoDoGrupoDeUsuariosDAO.getInstance().findByCriteria(new PermissaoDoGrupoDeUsuarios(), criterions);
            }            
            
            DefaultTableModel modelo = (DefaultTableModel) tabelaPermissoes.getModel();
            modelo.setNumRows(0);
            
            SistemaVisao sistemaVisao = new SistemaVisao();
            JMenuBar menuSistema = sistemaVisao.getMenu();
            Component[] modulos = menuSistema.getComponents();
            
            // Percorre os módulos.
            for ( int m = 0; m < modulos.length; m ++ ) {
                
                if ( modulos[m] instanceof JMenu )
                {
                    JMenu modulo = (JMenu) modulos[m];
                    Component[] telas = modulo.getMenuComponents();
                    
                    // Percorre as telas do módulo.
                    for ( int t = 0; t < telas.length; t ++ ) {
                        if ( telas[t] instanceof JMenuItem )
                        {
                            // Por padrão, todos itens desmarcados.
                            JMenuItem tela = (JMenuItem) telas[t];
                            boolean visualizar = false;
                            boolean inserir = false;
                            boolean atualizar = false;
                            boolean excluir = false;
                            boolean admin = false;
                            
                            // Verifica se o grupo já possui registro na permissão, e popula conforme registros.
                            for (int p = 0; p < permissoes.size(); p ++) {
                                PermissaoDoGrupoDeUsuarios permissaoDoGrupoDeUsuarios = (PermissaoDoGrupoDeUsuarios) permissoes.get(p);
                                
                                if ( permissaoDoGrupoDeUsuarios.getId().equals(tela.getName()) ) {
                                    visualizar = permissaoDoGrupoDeUsuarios.isVisualizar();
                                    inserir = permissaoDoGrupoDeUsuarios.isInserir();
                                    atualizar = permissaoDoGrupoDeUsuarios.isAtualizar();
                                    excluir = permissaoDoGrupoDeUsuarios.isExcluir();
                                    admin = permissaoDoGrupoDeUsuarios.isAdmin();
                                    
                                    break;
                                }
                            }
                            
                            // Popula a grid com os itens do menu, de seus respectivos módulos.
                            modelo.addRow(new Object[]{
                                tela.getName(), // ESTE É O ID DA TELA!!!
                                modulo.getText(), 
                                tela.getText(),
                                visualizar,
                                inserir,
                                atualizar,
                                excluir,
                                admin
                            });
                        }
                    }
                }
            }
            
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar grid: " + err.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
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
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelsPainel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPermissoes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        toolbar.setRollover(true);

        botaoSalvar.setText("Salvar");
        botaoSalvar.setFocusable(false);
        botaoSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });
        toolbar.add(botaoSalvar);

        botaoCancelar.setText("Cancelar");
        botaoCancelar.setFocusable(false);
        botaoCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });
        toolbar.add(botaoCancelar);

        labelsPainel.setBackground(java.awt.SystemColor.controlLtHighlight);

        jLabel1.setText("ID:");

        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("*");

        id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jPanel1.setBackground(java.awt.SystemColor.controlLtHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        tabelaPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Módulo", "Tela", "VISUALIZAR", "INSERIR", "ATUALIZAR", "EXCLUIR", "ADMIN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPermissoes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaPermissoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout labelsPainelLayout = new javax.swing.GroupLayout(labelsPainel);
        labelsPainel.setLayout(labelsPainelLayout);
        labelsPainelLayout.setHorizontalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(labelsPainelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelsPainelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        labelsPainelLayout.setVerticalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelsPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelsPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Grupo de usuários", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        if ( this.validarCampos() )
        {
            GrupoDeUsuarios grupoDeUsuarios = new GrupoDeUsuarios();
            if ( id.getText().length() > 0 )
            {
                grupoDeUsuarios.setId(Integer.parseInt(id.getText()));
            }
            grupoDeUsuarios.setNome(nome.getText());
            GrupoDeUsuariosDAO.getInstance().merge(grupoDeUsuarios);
            
            // Obter lista de permissões marcadas.
            DefaultTableModel modelo = (DefaultTableModel) tabelaPermissoes.getModel();
            
            for ( int c = 0; c < modelo.getRowCount(); c ++ ) {                
                PermissaoDoGrupoDeUsuarios permissaoDoGrupoDeUsuarios = new PermissaoDoGrupoDeUsuarios();
                permissaoDoGrupoDeUsuarios.setId((String) modelo.getValueAt(c, 0));
                permissaoDoGrupoDeUsuarios.setGrupoDeUsuarios(grupoDeUsuarios);
                permissaoDoGrupoDeUsuarios.setVisualizar((boolean) modelo.getValueAt(c, 3));
                permissaoDoGrupoDeUsuarios.setInserir((boolean) modelo.getValueAt(c, 4));
                permissaoDoGrupoDeUsuarios.setAtualizar((boolean) modelo.getValueAt(c, 5));
                permissaoDoGrupoDeUsuarios.setExcluir((boolean) modelo.getValueAt(c, 6));
                permissaoDoGrupoDeUsuarios.setAdmin((boolean) modelo.getValueAt(c, 7));                
                PermissaoDoGrupoDeUsuariosDAO.getInstance().merge(permissaoDoGrupoDeUsuarios);
            }

            JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            parent.atualizarGrid(grupoDeUsuarios.getId());
            this.setVisible(false);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModeloFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModeloFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModeloFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModeloFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModeloFormulario dialog = new ModeloFormulario(parent, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel labelsPainel;
    private javax.swing.JTextField nome;
    private javax.swing.JTable tabelaPermissoes;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
