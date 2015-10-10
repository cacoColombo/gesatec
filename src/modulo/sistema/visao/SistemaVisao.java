/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.visao;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modulo.administrativo.dao.GrupoDeUsuariosDAO;
import modulo.administrativo.dao.PermissaoDoGrupoDeUsuariosDAO;
import modulo.administrativo.negocio.GrupoDeUsuarios;
import modulo.administrativo.negocio.GrupoDoUsuario;
import modulo.administrativo.negocio.PermissaoDoGrupoDeUsuarios;
import modulo.administrativo.negocio.UserAccount;
import modulo.administrativo.visao.GrupoDeUsuariosBusca;
import modulo.administrativo.visao.UsuarioBusca;
import modulo.cadastro.visao.AtendenteBusca;
import modulo.cadastro.visao.CertificacaoBusca;
import modulo.cadastro.visao.ClienteBusca;
import modulo.cadastro.visao.EspecializacaoBusca;
import modulo.cadastro.visao.ProfissionalBusca;
import modulo.configuracao.visao.PadraoDeAtendimentoBusca;
import modulo.sistema.negocio.UsuarioLogado;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class SistemaVisao extends javax.swing.JFrame {

    /**
     * Creates new form Sistema
     */
    public SistemaVisao() 
    {
        initComponents();
        
        menu.add(Box.createRigidArea(new Dimension(100, 40)));
        this.setIconImage(new ImageIcon(this.getClass().getResource("/publico/imagens/logo.png")).getImage());
        
        submenuCadastros.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cadastros.png")));
        submenuCadastros.setEnabled(false);
        submenuAtendente.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/atendente.png")));
        submenuAtendente.setEnabled(false);
        submenuCertificacao.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/certificacao.png")));
        submenuCertificacao.setEnabled(false);
        submenuCliente.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cliente.png")));
        submenuCliente.setEnabled(false);
        submenuEspecializacao.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/especializacao.png")));
        submenuEspecializacao.setEnabled(false);
        submenuProfissional.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/profissional.png")));
        submenuProfissional.setEnabled(false);
        
        menuProcessos.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/processo.png")));
        menuProcessos.setEnabled(false);
        submenuAgenda.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/agenda.png")));
        submenuAgenda.setEnabled(false);
        submenuAtendimento.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/atendimento.png")));
        submenuAtendimento.setEnabled(false);
        submenuProntuario.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/prontuario.png")));
        submenuProntuario.setEnabled(false);
        
        menuConfiguracoes.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/configuracoes.png")));
        menuConfiguracoes.setEnabled(false);
        submenuPadraoDeAtendimento.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/padraoDeAtendimento.png")));
        submenuPadraoDeAtendimento.setEnabled(false);
        submenuTipoDeAtendimento.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/tipoDeAtendimento.png")));
        submenuTipoDeAtendimento.setEnabled(false);
        submenuTipoDeProntuario.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/tipoDeProntuario.png")));
        submenuTipoDeProntuario.setEnabled(false);
        
        menuAdministrativo.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/administrativo.png")));
        menuAdministrativo.setEnabled(false);
        submenuLogout.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/logout.png")));
        submenuLogout.setEnabled(false);
        submenuGrupoDeUsuarios.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/grupoDeUsuarios.png")));
        submenuGrupoDeUsuarios.setEnabled(false);
        submenuUsuario.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/usuario.png")));
        submenuUsuario.setEnabled(false);
        
        this.verificaPermissoesDoUsuario();
    }
    
    /**
     * Verifica as permissões do usuário, 
     * conforme permissões concebidas aos seus grupos.
     */
    private void verificaPermissoesDoUsuario() {
        UserAccount usuario = UsuarioLogado.getInstance().getUsuarioLogado();
        List<Object> gruposDoUsuario = UsuarioLogado.getInstance().getGruposDoUsuarioLogado();
        
        // Percorre os grupos de usuários, do usuário.
        for ( int i = 0; i < gruposDoUsuario.size(); i ++ ) {                
            GrupoDoUsuario grupoDoUsuario = (GrupoDoUsuario) gruposDoUsuario.get(i);
            
            // Obtém as permissões do grupo de usuários, do usuário.
            Conjunction find = Restrictions.conjunction();
            find.add(Restrictions.eq("grupoDeUsuarios", grupoDoUsuario.getGrupoDeUsuarios()));
            List<Object> permissoesDoGrupo = PermissaoDoGrupoDeUsuariosDAO.getInstance().findByCriteria(new PermissaoDoGrupoDeUsuarios(), find, Restrictions.disjunction());
            
            // Percorre as permissões do grupo de usuários, do usuário.
            for ( int x = 0; x < permissoesDoGrupo.size(); x ++ ) {
                PermissaoDoGrupoDeUsuarios permissao = (PermissaoDoGrupoDeUsuarios) permissoesDoGrupo.get(x);
                this.setaPermissaoDoUsuario(permissao);
            }
        }       
    }
    
    /**
     * Habilita item do menu correspondente 
     * a permissão de visualização do usuário.
     * 
     * @param permissao 
     */
    private void setaPermissaoDoUsuario(PermissaoDoGrupoDeUsuarios permissao)
    {
        JMenuBar menuSistema = this.getMenu();
        Component[] modulos = menuSistema.getComponents();

        // Percorre os módulos.
        loop: for ( int m = 0; m < modulos.length; m ++ ) {

            if ( modulos[m] instanceof JMenu )
            {
                JMenu modulo = (JMenu) modulos[m];
                Component[] telas = modulo.getMenuComponents();

                // Percorre as telas do módulo.
                for ( int t = 0; t < telas.length; t ++ ) {
                    if ( telas[t] instanceof JMenuItem )
                    {
                        JMenuItem tela = (JMenuItem) telas[t];
                        
                        if ( permissao.getId().equals(tela.getName()) )
                        {
                            // Se usuário possuir permissão de visualização na tela, habilita.
                            if ( permissao.isVisualizar() )
                            {
                                modulo.setEnabled(true);
                                tela.setEnabled(true);
                            }
                            
                            break loop;
                        }
                    }
                }
            }
        }
    }
    
    public JMenuBar getMenu() {
        return this.menu;
    }
    
    /**
     * Carrega o frame selecionado, no sistema.
     * 
     * @param frame 
     */
    public void carregarFrame(JInternalFrame frame)
    {
        panelConteudo.removeAll();
        panelConteudo.add(frame);
        
        try 
        {
            frame.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(SistemaVisao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelConteudo = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        submenuCadastros = new javax.swing.JMenu();
        submenuAtendente = new javax.swing.JMenuItem();
        submenuCertificacao = new javax.swing.JMenuItem();
        submenuCliente = new javax.swing.JMenuItem();
        submenuEspecializacao = new javax.swing.JMenuItem();
        submenuProfissional = new javax.swing.JMenuItem();
        menuProcessos = new javax.swing.JMenu();
        submenuAgenda = new javax.swing.JMenuItem();
        submenuAtendimento = new javax.swing.JMenuItem();
        submenuProntuario = new javax.swing.JMenuItem();
        menuConfiguracoes = new javax.swing.JMenu();
        submenuPadraoDeAtendimento = new javax.swing.JMenuItem();
        submenuTipoDeAtendimento = new javax.swing.JMenuItem();
        submenuTipoDeProntuario = new javax.swing.JMenuItem();
        menuAdministrativo = new javax.swing.JMenu();
        submenuLogout = new javax.swing.JMenuItem();
        submenuGrupoDeUsuarios = new javax.swing.JMenuItem();
        submenuUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelConteudoLayout = new javax.swing.GroupLayout(panelConteudo);
        panelConteudo.setLayout(panelConteudoLayout);
        panelConteudoLayout.setHorizontalGroup(
            panelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
        );
        panelConteudoLayout.setVerticalGroup(
            panelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menu.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N

        submenuCadastros.setText("Cadastros");
        submenuCadastros.setName("cadastros"); // NOI18N

        submenuAtendente.setText("Atendente");
        submenuAtendente.setName("atendente"); // NOI18N
        submenuAtendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuAtendenteActionPerformed(evt);
            }
        });
        submenuCadastros.add(submenuAtendente);

        submenuCertificacao.setText("Certificação");
        submenuCertificacao.setName("certificacao"); // NOI18N
        submenuCertificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuCertificacaoActionPerformed(evt);
            }
        });
        submenuCadastros.add(submenuCertificacao);

        submenuCliente.setText("Cliente");
        submenuCliente.setName("cliente"); // NOI18N
        submenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuClienteActionPerformed(evt);
            }
        });
        submenuCadastros.add(submenuCliente);

        submenuEspecializacao.setText("Especialização");
        submenuEspecializacao.setName("especializacao"); // NOI18N
        submenuEspecializacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuEspecializacaoActionPerformed(evt);
            }
        });
        submenuCadastros.add(submenuEspecializacao);

        submenuProfissional.setText("Profissional");
        submenuProfissional.setName("profissional"); // NOI18N
        submenuProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuProfissionalActionPerformed(evt);
            }
        });
        submenuCadastros.add(submenuProfissional);

        menu.add(submenuCadastros);

        menuProcessos.setText("Processos");
        menuProcessos.setName("processos"); // NOI18N

        submenuAgenda.setText("Agenda");
        submenuAgenda.setName("agenda"); // NOI18N
        submenuAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuAgendaActionPerformed(evt);
            }
        });
        menuProcessos.add(submenuAgenda);

        submenuAtendimento.setText("Atendimento");
        submenuAtendimento.setName("atendimento"); // NOI18N
        menuProcessos.add(submenuAtendimento);

        submenuProntuario.setText("Prontuário");
        submenuProntuario.setName("prontuario"); // NOI18N
        menuProcessos.add(submenuProntuario);

        menu.add(menuProcessos);

        menuConfiguracoes.setText("Configurações");
        menuConfiguracoes.setName("configuracoes"); // NOI18N

        submenuPadraoDeAtendimento.setText("Padrão de atendimento");
        submenuPadraoDeAtendimento.setName("padraodeatendimento"); // NOI18N
        submenuPadraoDeAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuPadraoDeAtendimentoActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(submenuPadraoDeAtendimento);

        submenuTipoDeAtendimento.setText("Tipo de atendimento");
        submenuTipoDeAtendimento.setName("tipodeatendimento"); // NOI18N
        menuConfiguracoes.add(submenuTipoDeAtendimento);

        submenuTipoDeProntuario.setText("Tipo de prontuário");
        submenuTipoDeProntuario.setName("tipodeprontuario"); // NOI18N
        menuConfiguracoes.add(submenuTipoDeProntuario);

        menu.add(menuConfiguracoes);

        menuAdministrativo.setText("Administrativo");
        menuAdministrativo.setName("administrativo"); // NOI18N

        submenuLogout.setText("Efetuar logout");
        submenuLogout.setName("efetuarlogout"); // NOI18N
        submenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuLogoutActionPerformed(evt);
            }
        });
        menuAdministrativo.add(submenuLogout);

        submenuGrupoDeUsuarios.setText("Grupo de usuários");
        submenuGrupoDeUsuarios.setName("grupodeusuarios"); // NOI18N
        submenuGrupoDeUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuGrupoDeUsuariosActionPerformed(evt);
            }
        });
        menuAdministrativo.add(submenuGrupoDeUsuarios);

        submenuUsuario.setText("Usuário");
        submenuUsuario.setName("usuario"); // NOI18N
        submenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuUsuarioActionPerformed(evt);
            }
        });
        menuAdministrativo.add(submenuUsuario);

        menu.add(menuAdministrativo);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submenuAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuAgendaActionPerformed
        /**
        OcorrenciaBusca ocorrenciaVisao = new OcorrenciaBusca();
        panelConteudo.removeAll();
        panelConteudo.add(ocorrenciaVisao);
        
        try 
        {
            ocorrenciaVisao.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ocorrenciaVisao.setVisible(true);
        */
    }//GEN-LAST:event_submenuAgendaActionPerformed

    private void submenuAtendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuAtendenteActionPerformed
        AtendenteBusca atendenteBusca = new AtendenteBusca(); 
        this.carregarFrame(atendenteBusca);
    }//GEN-LAST:event_submenuAtendenteActionPerformed

    private void submenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuLogoutActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja sair do sistema?", "Atenção!", JOptionPane.YES_NO_OPTION);
            
        if ( reply == JOptionPane.YES_OPTION ) 
        {
            LoginVisao login = new LoginVisao();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_submenuLogoutActionPerformed

    private void submenuCertificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuCertificacaoActionPerformed
        CertificacaoBusca certificacaoBusca = new CertificacaoBusca(); 
        this.carregarFrame(certificacaoBusca);
    }//GEN-LAST:event_submenuCertificacaoActionPerformed

    private void submenuGrupoDeUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuGrupoDeUsuariosActionPerformed
        GrupoDeUsuariosBusca grupoDeUsuariosBusca = new GrupoDeUsuariosBusca(); 
        this.carregarFrame(grupoDeUsuariosBusca);
    }//GEN-LAST:event_submenuGrupoDeUsuariosActionPerformed

    private void submenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuClienteActionPerformed
        ClienteBusca clienteBusca = new ClienteBusca(); 
        this.carregarFrame(clienteBusca);
    }//GEN-LAST:event_submenuClienteActionPerformed

    private void submenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuUsuarioActionPerformed
        UsuarioBusca usuarioBusca = new UsuarioBusca(); 
        this.carregarFrame(usuarioBusca);
    }//GEN-LAST:event_submenuUsuarioActionPerformed

    private void submenuEspecializacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuEspecializacaoActionPerformed
        EspecializacaoBusca especializacaoBusca =  new EspecializacaoBusca();
        this.carregarFrame(especializacaoBusca);
    }//GEN-LAST:event_submenuEspecializacaoActionPerformed

    private void submenuProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuProfissionalActionPerformed
        ProfissionalBusca profissionalBusca = new ProfissionalBusca();
        this.carregarFrame(profissionalBusca);
    }//GEN-LAST:event_submenuProfissionalActionPerformed

    private void submenuPadraoDeAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuPadraoDeAtendimentoActionPerformed
        PadraoDeAtendimentoBusca padraoDeAtendimentoBusca = new PadraoDeAtendimentoBusca();
        this.carregarFrame(padraoDeAtendimentoBusca);
    }//GEN-LAST:event_submenuPadraoDeAtendimentoActionPerformed

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
            java.util.logging.Logger.getLogger(SistemaVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaVisao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaVisao().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuAdministrativo;
    private javax.swing.JMenu menuConfiguracoes;
    private javax.swing.JMenu menuProcessos;
    private javax.swing.JPanel panelConteudo;
    private javax.swing.JMenuItem submenuAgenda;
    private javax.swing.JMenuItem submenuAtendente;
    private javax.swing.JMenuItem submenuAtendimento;
    private javax.swing.JMenu submenuCadastros;
    private javax.swing.JMenuItem submenuCertificacao;
    private javax.swing.JMenuItem submenuCliente;
    private javax.swing.JMenuItem submenuEspecializacao;
    private javax.swing.JMenuItem submenuGrupoDeUsuarios;
    private javax.swing.JMenuItem submenuLogout;
    private javax.swing.JMenuItem submenuPadraoDeAtendimento;
    private javax.swing.JMenuItem submenuProfissional;
    private javax.swing.JMenuItem submenuProntuario;
    private javax.swing.JMenuItem submenuTipoDeAtendimento;
    private javax.swing.JMenuItem submenuTipoDeProntuario;
    private javax.swing.JMenuItem submenuUsuario;
    // End of variables declaration//GEN-END:variables
}
