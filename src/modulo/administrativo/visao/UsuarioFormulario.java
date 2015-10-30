/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.administrativo.visao;

import java.util.ArrayList;
import modulo.cadastro.visao.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modulo.administrativo.dao.GrupoDeUsuariosDAO;
import modulo.administrativo.dao.GrupoDoUsuarioDAO;
import modulo.administrativo.dao.UsuarioDAO;
import modulo.administrativo.negocio.GrupoDeUsuarios;
import modulo.administrativo.negocio.GrupoDoUsuario;
import modulo.administrativo.negocio.UserAccount;
import modulo.sistema.negocio.SOptionPane;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class UsuarioFormulario extends javax.swing.JDialog {

    public static UsuarioBusca parent;
    public UserAccount useraccount;
    List<Object> gruposDoUsuario;
    
    /**
     * Creates new form AtendenteFormulario
     */
    public UsuarioFormulario(UsuarioBusca parent, boolean modal) {
        this.parent = parent;
        this.setModal(modal);
        this.setLocation(600, 530);
        initComponents();
        
        tabelaDeGrupos.setSelectionBackground(new java.awt.Color(22, 160, 133));
        tabelaDeGrupos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        
        botaoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/salvar.png")));
        botaoCancelar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cancelar.png")));
        botaoAdicionarGrupo.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/add.png")));
        botaoRemoverGrupo.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/remover.png")));
        
        tabelaDeGrupos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(tabelaDeGrupos.getSelectedRow() > tabelaDeGrupos.getRowCount()){
                    botaoRemoverGrupo.setEnabled(false);
                }
                else{
                    botaoRemoverGrupo.setEnabled(true);
                }
            }
        });
    }
    
    public void popularCampos(UserAccount usuario) {
        try {
            this.useraccount = usuario;
            id.setText(Integer.toString(usuario.getId()));
            name.setText(usuario.getName());
            login.setText(usuario.getLogin());
            ativo.setSelected(usuario.isActive());

            if ( !Integer.toString(usuario.getId()).isEmpty() )
            {
                name.setEnabled(false);
                login.setEnabled(false);
            }

            // Obter grupos de usuários, do usuário.
            Conjunction and = Restrictions.conjunction();
            and.add(Restrictions.eq("usuario", usuario));
            gruposDoUsuario = GrupoDoUsuarioDAO.getInstance().findByCriteria(new GrupoDoUsuario(), and, Restrictions.disjunction());

            // Popula combobox de grupos de usuários, somente com os grupos não pertencentes ao usuário editado.
            grupoDeUsuarios.addItem("");
            List<Object> grupos = GrupoDeUsuariosDAO.getInstance().findAll(new GrupoDeUsuarios());

            for ( int i = 0; i < grupos.size(); i ++ ) {                
                GrupoDeUsuarios grupo = (GrupoDeUsuarios) grupos.get(i);
                boolean possuiGrupo = false;

                for ( int g = 0; g < gruposDoUsuario.size(); g++ ) {
                    // Somente adicionar, se não existir no list de gruposDoUsuario
                    GrupoDoUsuario grupoDoUsuario = (GrupoDoUsuario) gruposDoUsuario.get(g);

                    if ( grupoDoUsuario.getGrupoDeUsuarios().getId() == grupo.getId() )
                    {
                        possuiGrupo = true;
                        break;
                    }
                }

                if ( !possuiGrupo ) {
                    grupoDeUsuarios.addItem(grupo);
                }
            } 

            // Popula tabela de grupos de usuários, com os grupos pertencentes ao usuário editado.
            DefaultTableModel modelo = (DefaultTableModel) tabelaDeGrupos.getModel();
            for ( int g = 0; g < gruposDoUsuario.size(); g++ ) {
                GrupoDoUsuario grupoDoUsuario = (GrupoDoUsuario) gruposDoUsuario.get(g);
                modelo.addRow(new Object[]{grupoDoUsuario.getGrupoDeUsuarios().getId(), grupoDoUsuario.getGrupoDeUsuarios().getNome()});
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean validarCampos() {
        try {
            if( !senha.getText().isEmpty() && senha.getText().length() < 8 ){
                throw new Exception("O campo 'Senha' deve ter no mínimo 8 caracteres");
            }
            
            return true;
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
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
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pessoa = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        ativo = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        grupoDeUsuarios = new javax.swing.JComboBox();
        botaoAdicionarGrupo = new javax.swing.JButton();
        botaoRemoverGrupo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDeGrupos = new javax.swing.JTable();

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

        jLabel3.setText("Login:");

        id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel6.setText("Senha:");

        jLabel8.setText("Nome:");

        pessoa.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel9.setText("Ativo:");

        javax.swing.GroupLayout labelsPainelLayout = new javax.swing.GroupLayout(labelsPainel);
        labelsPainel.setLayout(labelsPainelLayout);
        labelsPainelLayout.setHorizontalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(pessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(labelsPainelLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelsPainelLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
                            .addGroup(labelsPainelLayout.createSequentialGroup()
                                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ativo)
                                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        labelsPainelLayout.setVerticalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ativo)
                    .addComponent(jLabel9))
                .addGap(355, 355, 355))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelsPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelsPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Usuário", jPanel2);

        jPanel3.setBackground(java.awt.SystemColor.controlLtHighlight);

        jLabel7.setText("Grupo de usuários:");

        grupoDeUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoDeUsuariosActionPerformed(evt);
            }
        });

        botaoAdicionarGrupo.setText("Adicionar grupo");
        botaoAdicionarGrupo.setEnabled(false);
        botaoAdicionarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarGrupoActionPerformed(evt);
            }
        });

        botaoRemoverGrupo.setText("Remover grupo");
        botaoRemoverGrupo.setEnabled(false);
        botaoRemoverGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverGrupoActionPerformed(evt);
            }
        });

        tabelaDeGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaDeGrupos);
        tabelaDeGrupos.getColumnModel().getColumn(1).setPreferredWidth(300);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(botaoAdicionarGrupo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverGrupo)
                                .addContainerGap(114, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(grupoDeUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(grupoDeUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAdicionarGrupo)
                    .addComponent(botaoRemoverGrupo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Grupos do usuário", jPanel1);

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
        try {
            if ( this.validarCampos() )
            {
                useraccount.setName(name.getText());
                useraccount.setLogin(login.getText());
                useraccount.setActive(ativo.isSelected());
                if ( !senha.getText().isEmpty() )
                {
                    useraccount.setPassword(senha.getText());
                }
                UsuarioDAO.getInstance().merge(useraccount);

                // Deletar todos os grupos do usuário, e registrá-los novamente.
                for ( int g = 0; g < gruposDoUsuario.size(); g++ ) {
                    // Somente adicionar, se não existir no list de gruposDoUsuario
                    GrupoDoUsuario grupoDoUsuario = (GrupoDoUsuario) gruposDoUsuario.get(g);
                    GrupoDoUsuarioDAO.getInstance().remove(grupoDoUsuario);
                }

                DefaultTableModel modelo = (DefaultTableModel) tabelaDeGrupos.getModel();
                for ( int i = 0; i < modelo.getRowCount(); i++ )
                {
                    GrupoDeUsuarios grupo = new GrupoDeUsuarios();
                    grupo.setId((int)modelo.getValueAt(i, 0));

                    GrupoDoUsuario grupoDoUsuario = new GrupoDoUsuario();
                    grupoDoUsuario.setUsuario(useraccount);
                    grupoDoUsuario.setGrupoDeUsuarios(grupo);

                    GrupoDoUsuarioDAO.getInstance().merge(grupoDoUsuario);
                }

                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                List<Object> registro = new ArrayList();
                registro.add(useraccount);

                parent.atualizarGrid(useraccount.getId(), registro);
                this.setVisible(false);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void grupoDeUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoDeUsuariosActionPerformed
        if ( grupoDeUsuarios.getSelectedItem().toString().isEmpty() ) {
            botaoAdicionarGrupo.setEnabled(false);
        } else {
            botaoAdicionarGrupo.setEnabled(true);
        }
    }//GEN-LAST:event_grupoDeUsuariosActionPerformed

    private void botaoAdicionarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarGrupoActionPerformed
        try {
            GrupoDeUsuarios grupo = (GrupoDeUsuarios) grupoDeUsuarios.getSelectedItem();
            DefaultTableModel modelo = (DefaultTableModel) tabelaDeGrupos.getModel();
            modelo.addRow(new Object[]{grupo.getId(), grupo.getNome()});
            grupoDeUsuarios.removeItem(grupo);
            grupoDeUsuarios.setSelectedItem("");
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoAdicionarGrupoActionPerformed

    private void botaoRemoverGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverGrupoActionPerformed
        try {
            int selected = tabelaDeGrupos.getSelectedRow();
            Object registro = tabelaDeGrupos.getValueAt(selected, 0);
            int grupo_id = Integer.parseInt(registro.toString());

            Object grupo = GrupoDeUsuariosDAO.getInstance().getById(new GrupoDeUsuarios(), grupo_id);
            DefaultTableModel modelo = (DefaultTableModel) tabelaDeGrupos.getModel();
            modelo.removeRow(selected);
            grupoDeUsuarios.addItem(grupo);
            botaoRemoverGrupo.setEnabled(false);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoRemoverGrupoActionPerformed

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
            java.util.logging.Logger.getLogger(CertificacaoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CertificacaoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CertificacaoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CertificacaoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UsuarioFormulario dialog = new UsuarioFormulario(parent, true);
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
    private javax.swing.JCheckBox ativo;
    private javax.swing.JButton botaoAdicionarGrupo;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoRemoverGrupo;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox grupoDeUsuarios;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel labelsPainel;
    private javax.swing.JTextField login;
    private javax.swing.JTextField name;
    private javax.swing.JLabel pessoa;
    private javax.swing.JPasswordField senha;
    private javax.swing.JTable tabelaDeGrupos;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
