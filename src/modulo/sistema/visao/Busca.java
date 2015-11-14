/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.sistema.visao;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modulo.administrativo.negocio.PermissaoDoGrupoDeUsuarios;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.negocio.UsuarioLogado;

/**
 *
 * @author augusto
 */
public abstract class Busca extends javax.swing.JInternalFrame {
    
    private boolean forcarDesabilitarBotaoExcluir = false;
    private boolean forcarDesabilitarBotaoEditar = false;
    
    /**
     * Creates new form ModeloBusca
     */
    public Busca() {
        initComponents();
    }

    public boolean isForcarDesabilitarBotaoExcluir() {
        return forcarDesabilitarBotaoExcluir;
    }

    public void setForcarDesabilitarBotaoExcluir(boolean forcarDesabilitarBotaoExcluir) {
        this.forcarDesabilitarBotaoExcluir = forcarDesabilitarBotaoExcluir;
    }

    public boolean isForcarDesabilitarBotaoEditar() {
        return forcarDesabilitarBotaoEditar;
    }

    public void setForcarDesabilitarBotaoEditar(boolean forcarDesabilitarBotaoEditar) {
        this.forcarDesabilitarBotaoEditar = forcarDesabilitarBotaoEditar;
    }

    public JButton getBotaoAtualizar() {
        return botaoAtualizar;
    }

    public void setBotaoAtualizar(JButton botaoAtualizar) {
        this.botaoAtualizar = botaoAtualizar;
    }

    public JButton getBotaoBuscar() {
        return botaoBuscar;
    }

    public void setBotaoBuscar(JButton botaoBuscar) {
        this.botaoBuscar = botaoBuscar;
    }

    public JButton getBotaoEditar() {
        return botaoEditar;
    }

    public void setBotaoEditar(JButton botaoEditar) {
        this.botaoEditar = botaoEditar;
    }

    public JButton getBotaoExcluir() {
        return botaoExcluir;
    }

    public void setBotaoExcluir(JButton botaoExcluir) {
        this.botaoExcluir = botaoExcluir;
    }

    public JButton getBotaoNovo() {
        return botaoNovo;
    }

    public void setBotaoNovo(JButton botaoNovo) {
        this.botaoNovo = botaoNovo;
    }

    public JTextField getCampoBusca() {
        return campoBusca;
    }

    public void setCampoBusca(JTextField campoBusca) {
        this.campoBusca = campoBusca;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    public void setToolbar(JToolBar toolbar) {
        this.toolbar = toolbar;
    }
    
    public abstract DefaultTableModel construirGrid();
    public abstract void atualizarGrid(int selecionar, List<Object> registros);
    public abstract void botaoNovoActionPerformed(java.awt.event.ActionEvent evt);
    public abstract void botaoEditarActionPerformed(java.awt.event.ActionEvent evt);
    public abstract void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt);
    public abstract void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt);
    
    private void campoBuscaKeyPressed(java.awt.event.KeyEvent evt) {                                      
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            this.botaoBuscarActionPerformed(null);
        }
    }

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        atualizarGrid(-1, new ArrayList());
    }//GEN-LAST:event_botaoAtualizarActionPerformed
    
    public void customizacaoDosComponentes() {
        setBorder(null);
        getTabela().setSelectionBackground(new java.awt.Color(22, 160, 133));
        getTabela().setSelectionForeground(new java.awt.Color(255, 255, 255));

        getBotaoNovo().setIcon(new ImageIcon(getClass().getResource("/publico/imagens/novo.png")));
        getBotaoEditar().setIcon(new ImageIcon(getClass().getResource("/publico/imagens/editar.png")));
        getBotaoExcluir().setIcon(new ImageIcon(getClass().getResource("/publico/imagens/excluir.png")));
        getBotaoAtualizar().setIcon(new ImageIcon(getClass().getResource("/publico/imagens/atualizar.png")));
        getBotaoBuscar().setIcon(new ImageIcon(getClass().getResource("/publico/imagens/buscar.png")));
        
        getBotaoNovo().setEnabled(false);
        getBotaoEditar().setEnabled(false);
        getBotaoExcluir().setEnabled(false);
        setForcarDesabilitarBotaoEditar(true);
        setForcarDesabilitarBotaoExcluir(true);
        
        atualizarGrid(-1, new ArrayList());
        
        getTabela().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
               eventoAoSelecionarNaTabela();
            }
        });
        
        this.setaPermissoesDoUsuarioNaTela();
    }
    
    /**
     * Evento executado ao selecionar um registro na tabela.
     */
    public void eventoAoSelecionarNaTabela() {
        if (getTabela().getSelectedRow() > getTabela().getRowCount()) {
            getBotaoEditar().setEnabled(false);
            getBotaoExcluir().setEnabled(false);
        } else {
            getBotaoExcluir().setEnabled(!forcarDesabilitarBotaoExcluir);
            getBotaoEditar().setEnabled(!forcarDesabilitarBotaoEditar);
        }
    }
    
    /**
     * Verifica e seta as permissões concebidas ao usuário na tela acessada.
     */
    public void setaPermissoesDoUsuarioNaTela() {
        try {
            LinkedList<Object> permissoes = UsuarioLogado.getInstance().getPermissoesDosGruposDoUsuarioLogado();

            for ( int i = 0; i < permissoes.size(); i ++ ) {
                PermissaoDoGrupoDeUsuarios permissao = (PermissaoDoGrupoDeUsuarios) permissoes.get(i);

                if ( permissao.getId().equals(this.getName()) ) {

                    // Usuário possui permissão de inserção.
                    if ( permissao.isInserir() || permissao.isAdmin() )
                    {
                        getBotaoNovo().setEnabled(true);
                    }

                    // Usuário possui permissão de edição.
                    if ( permissao.isAtualizar() || permissao.isAdmin() )
                    {
                        setForcarDesabilitarBotaoEditar(false);
                    }

                    // Usuário possui permissão de exclusão.
                    if ( permissao.isExcluir() || permissao.isAdmin() )
                    {
                        setForcarDesabilitarBotaoExcluir(false);
                    }
                }
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents() {

        toolbar = new javax.swing.JToolBar();
        botaoNovo = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        botaoBuscar = new javax.swing.JButton();
        campoBusca = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setTitle("Nome da tela");

		toolbar.setFloatable(false);
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
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });
        toolbar.add(botaoExcluir);

        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.setFocusable(false);
        botaoAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });
        toolbar.add(botaoAtualizar);

        jPanel1.setBackground(java.awt.SystemColor.controlLtHighlight);

        tabela.setModel(construirGrid());
        jScrollPane1.setViewportView(tabela);

        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoBuscaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoBusca)
                    .addComponent(botaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
	this.customizacaoDosComponentes();
    }// </editor-fold>//GEN-END:initComponents

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
