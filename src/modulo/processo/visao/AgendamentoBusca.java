/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.visao;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import modulo.cadastro.dao.ProfissionalDAO;
import modulo.cadastro.dao.TipoDeAtendimentoDoProfissionalDAO;
import modulo.cadastro.negocio.Profissional;
import modulo.cadastro.negocio.TipoDeAtendimentoDoProfissional;
import modulo.configuracao.dao.TipoDeAtendimentoDAO;
import modulo.configuracao.negocio.TipoDeAtendimento;
import modulo.processo.dao.AgendamentoDAO;
import modulo.processo.negocio.Agendamento;
import modulo.processo.negocio.StatusAgendamento;
import modulo.sistema.negocio.SOptionPane;
import modulo.sistema.visao.Busca;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public final class AgendamentoBusca extends Busca {

    public static AgendamentoFormulario form;
    
    /**
     * Creates new form AgendamentoBusca
     */
    public AgendamentoBusca() {
        this.setName("agenda");
        initComponents();
        setTitle("Agendamento");
        getBotaoNovo().setEnabled(false);
        
        try {
            this.populaComboDeProfissionais(false);
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Popula combo de tipos de atendimentos.
     * 
     */
    private void populaComboDeTiposDeAtendimentos(boolean empty) {
        TipoDeAtendimento tipoDeAtend = new TipoDeAtendimento();
        tipoDeAtend.setNome(" ");
        tipoDeAtendimento.removeAllItems();
        tipoDeAtendimento.addItem(tipoDeAtend);
        
        if ( !empty ) {
            List<Object> tiposDeAtendimento = TipoDeAtendimentoDAO.getInstance().findAll(new TipoDeAtendimento());
            for ( Object object : tiposDeAtendimento ) { 
                TipoDeAtendimento tipoDeAtendimentoObj = (TipoDeAtendimento) object;
                tipoDeAtendimento.addItem(tipoDeAtendimentoObj);
            }
        }
    }
    
    /**
     * Popula combo de profissionais
     */
    private void populaComboDeProfissionais(boolean empty) {
        Profissional prof = new Profissional();
        prof.setNome(" ");
        profissional.removeAllItems();
        profissional.addItem(prof);
        
        if ( !empty ) {
            List<Object> profissionais = ProfissionalDAO.getInstance().findAll(new Profissional());
            for ( Object object : profissionais ) { 
                Profissional profiss = (Profissional) object;
                profissional.addItem(profiss);
            } 
        }
    }
    
    @Override
    public void eventoAoSelecionarNaTabela() {
        getBotaoNovo().setEnabled(false);
        getBotaoEditar().setEnabled(false);
        getBotaoExcluir().setEnabled(false);
        
        int selected = getTabela().getSelectedRow();
        
        if ( selected != -1 ) {        
            Object registro = getTabela().getValueAt(selected, 3);
            String status = registro.toString();

            StatusAgendamento statusAgendamento = new StatusAgendamento();

            if ( status.equals(statusAgendamento.STATUS_AGENDA_LIVRE) ) {
                getBotaoNovo().setEnabled(true);
            } else if ( !status.equals(statusAgendamento.STATUS_AGENDA_INDISPONIVEL) ) {
                getBotaoEditar().setEnabled(true);
                getBotaoExcluir().setEnabled(true);
            }
        }
    }
    
    @Override
    public JButton getBotaoAtualizar() {
        return this.botaoAtualizar;
    }

    @Override
    public void setBotaoAtualizar(JButton botaoAtualizar) {
        this.botaoAtualizar = botaoAtualizar;
    }

    @Override
    public JButton getBotaoEditar() {
        return this.botaoEditar;
    }

    @Override
    public void setBotaoEditar(JButton botaoEditar) {
        this.botaoEditar = botaoEditar;
    }
    
    @Override
    public JButton getBotaoExcluir() {
        return this.botaoExcluir;
    }

    @Override
    public void setBotaoExcluir(JButton botaoExcluir) {
        this.botaoExcluir = botaoExcluir;
    }

    @Override
    public JButton getBotaoNovo() {
        return this.botaoNovo;
    }

    @Override
    public void setBotaoNovo(JButton botaoNovo) {
        this.botaoNovo = botaoNovo;
    }
    
    @Override
    public JTable getTabela() {
        return this.tabela;
    }

    @Override
    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    @Override
    public JToolBar getToolbar() {
        return this.toolbar;
    }

    @Override
    public void setToolbar(JToolBar toolbar) {
        this.toolbar = toolbar;
    }
    
    @Override
    public JButton getBotaoBuscar() {
        return this.botaoBuscar;
    }

    @Override
    public void setBotaoBuscar(JButton botaoBuscar) {
        this.botaoBuscar = botaoBuscar;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        calendario = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        tipoDeAtendimento = new javax.swing.JComboBox();
        profissional = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        botaoBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setBorder(null);

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

        jPanel4.setBackground(java.awt.SystemColor.controlLtHighlight);
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        calendario.setBackground(java.awt.SystemColor.controlLtHighlight);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
        );

        jLabel1.setText("Profissional");

        tipoDeAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDeAtendimentoActionPerformed(evt);
            }
        });

        profissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profissionalActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de atendimento");

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profissional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(botaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(tipoDeAtendimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoDeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoBuscar)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Filtros", jPanel2);

        jPanel3.setBackground(java.awt.SystemColor.controlLtHighlight);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Horário", "Situaçao", "Tipo de atendimento", "Profissional", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(15);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
	customizacaoDosComponentes();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public DefaultTableModel construirGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizarGrid(int selecionar, List<Object> horarios) {
        try {
            // Default
            if ( horarios.isEmpty() )
            {      
                Date data = new Date(calendario.getDate().getTime());
                AgendamentoDAO agendamentoDao = new AgendamentoDAO();
                horarios = agendamentoDao.obterHorariosParaAgendamento(data, 0, 0);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            for ( int i = 0; i < horarios.size(); i ++ ) { 
                Object[] horario = (Object[]) horarios.get(i);
                
                Profissional profissa = new Profissional();
                profissa.setId((int) horario[10]);
                profissa.setNome((String) horario[11]);
                
                modelo.addRow(new Object[]{
                    horario[4], //ID Agendamento
                    horario[12], //Data
                    horario[13], //Horário
                    horario[5], //Situação
                    horario[7], //Tipo de atendimento
                    profissa, //Profissional
                    horario[9], //Cliente
                });
                
                // Verifica item a selecionar
                if ( (horario[4] != null) && horario[4].toString().equals(Integer.toString(selecionar)) )
                {
                    getTabela().addRowSelectionInterval(i, i);
                }
            }
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        try {
            int selected = getTabela().getSelectedRow();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
            java.util.Date data = simpleDateFormat.parse(getTabela().getValueAt(selected, 1).toString());
            java.util.Date horario = simpleTimeFormat.parse(getTabela().getValueAt(selected, 2).toString());

            Agendamento agendamento = new Agendamento();
            agendamento.setDataAgendada(new java.sql.Date(data.getTime()));
            agendamento.setHorarioAgendado(new java.sql.Time(horario.getTime()));
            agendamento.setProfissional((Profissional) getTabela().getValueAt(selected, 5));

            form = new AgendamentoFormulario(this, true);
            form.popularCampos(agendamento);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoNovoActionPerformed

    @Override
    public void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        try {
            int selected = getTabela().getSelectedRow();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
            java.util.Date data = simpleDateFormat.parse(getTabela().getValueAt(selected, 1).toString());
            java.util.Date horario = simpleTimeFormat.parse(getTabela().getValueAt(selected, 2).toString());

            Agendamento agendamento = new Agendamento();
            agendamento.setId(Integer.parseInt(getTabela().getValueAt(selected, 0).toString()));
            agendamento.setDataAgendada(new java.sql.Date(data.getTime()));
            agendamento.setHorarioAgendado(new java.sql.Time(horario.getTime()));
            agendamento.setProfissional((Profissional) getTabela().getValueAt(selected, 5));

            form = new AgendamentoFormulario(this, true);
            form.popularCampos(agendamento);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoEditarActionPerformed

    @Override
    public void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoExcluirActionPerformed

    public void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        atualizarGrid(-1, new ArrayList());
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    @Override
    public void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        try {
            Date data = new Date(calendario.getDate().getTime());
            Profissional profiss = (Profissional) profissional.getSelectedItem();
            TipoDeAtendimento tipoDeAtend = (TipoDeAtendimento) tipoDeAtendimento.getSelectedItem();
            
            AgendamentoDAO agendamentoDao = new AgendamentoDAO();
            List<Object> horarios = agendamentoDao.obterHorariosParaAgendamento(data, profiss.getId(), tipoDeAtend.getId());
            
            DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();
            modelo.setNumRows(0);
            
            if ( !horarios.isEmpty() ) {
                atualizarGrid(-1, horarios);
            }
            
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void tipoDeAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDeAtendimentoActionPerformed
       
    }//GEN-LAST:event_tipoDeAtendimentoActionPerformed

    private void profissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profissionalActionPerformed
        try {
            // Obtém todos os tipos de atendimento do profissional selecionado.
            Profissional profissionalSelecionado = (Profissional) profissional.getSelectedItem();
            
            if ( !(profissionalSelecionado.toString().equals(" ")) )
            {
                Conjunction and = Restrictions.conjunction();
                and.add(Restrictions.eq("profissional", profissionalSelecionado));
                List<Object> tiposDeAtendimentosDosProfissionais = TipoDeAtendimentoDoProfissionalDAO.getInstance().findByCriteria(new TipoDeAtendimentoDoProfissional(), and, Restrictions.disjunction());

                TipoDeAtendimento tipoDeAtend = new TipoDeAtendimento();
                tipoDeAtend.setNome(" ");
                tipoDeAtendimento.removeAllItems();
                tipoDeAtendimento.addItem(tipoDeAtend);

                for ( Object object : tiposDeAtendimentosDosProfissionais ) { 
                    TipoDeAtendimentoDoProfissional tipoDeAtendimentoDoProfissional = (TipoDeAtendimentoDoProfissional) object;
                    tipoDeAtendimento.addItem(tipoDeAtendimentoDoProfissional.getTipoDeAtendimento());
                } 
            } else {
                this.populaComboDeTiposDeAtendimentos(false);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_profissionalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private com.toedter.calendar.JCalendar calendario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox profissional;
    private javax.swing.JTable tabela;
    private javax.swing.JComboBox tipoDeAtendimento;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
