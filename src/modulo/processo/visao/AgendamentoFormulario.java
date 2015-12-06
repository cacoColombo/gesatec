/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.processo.visao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modulo.administrativo.negocio.UserAccount;
import modulo.cadastro.dao.ClienteDAO;
import modulo.cadastro.dao.ProfissionalDAO;
import modulo.cadastro.dao.TipoDeAtendimentoDoProfissionalDAO;
import modulo.cadastro.negocio.Cliente;
import modulo.cadastro.negocio.Profissional;
import modulo.cadastro.negocio.TipoDeAtendimentoDoProfissional;
import modulo.cadastro.visao.ClienteFormulario;
import modulo.configuracao.negocio.TipoDeAtendimento;
import modulo.processo.dao.AgendamentoDAO;
import modulo.processo.dao.StatusAgendamentoDAO;
import modulo.processo.negocio.Agendamento;
import modulo.processo.negocio.StatusAgendamento;
import modulo.processo.negocio.TipoDeRepeticao;
import modulo.sistema.negocio.SOptionPane;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class AgendamentoFormulario extends javax.swing.JDialog {

    public static AgendamentoBusca parent;
    public Agendamento agendamento;
    
    /**
     * Creates new form AtendenteFormulario
     */
    public AgendamentoFormulario(AgendamentoBusca parent, boolean modal) {
        this.parent = parent;
        this.setModal(modal);
        this.setLocation(600, 530);
        initComponents();
        
        setTitle("Registro de agendamento");
        botaoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/salvar.png")));
        botaoCancelar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cancelar.png")));
        
        try {
            telefoneCelularCliente.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) ####-####")));
        } catch ( Exception err ) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
            
        statusAgendamento.setEnabled(false);
        data.setEnabled(false);
        horario.setEnabled(false);
    }
    
    public void popularCampos(Agendamento agendamento) {
        try {
            this.agendamento = agendamento;
            
            // Popula combo de status de agendamento.
            List<Object> status = StatusAgendamentoDAO.getInstance().findAll(new StatusAgendamento());
            for ( Object object : status ) { 
                StatusAgendamento statusAgendament = (StatusAgendamento) object;
                statusAgendamento.addItem(statusAgendament);
                
                if ( agendamento.getStatusAgendamento().getId() != 0 ) {
                    
                    if ( statusAgendament.getId() == agendamento.getStatusAgendamento().getId() ) {
                        statusAgendamento.setSelectedItem(statusAgendament);
                        statusAgendamento.setEnabled(true);
                    }
                } else if ( statusAgendament.getId() == statusAgendament.STATUS_AGENDA_AGENDADO ) {
                    // Para novos agendamentos, status inicial é sempre AGENDADO.
                    statusAgendamento.setSelectedItem(statusAgendament);
                }
            }
           
            // Popula combo de profisisonal disponíveis na data e horários selecionado.
            Profissional prof = new Profissional();
            prof.setNome(" ");
            profissional.removeAllItems();
            profissional.addItem(prof);
            AgendamentoDAO agendamentoDao = new AgendamentoDAO();
            List<Object> horarios = agendamentoDao.obterHorariosParaAgendamento(agendamento.getDataAgendada(), 0, 0, agendamento.getId(), agendamento.getHorarioAgendado());
            for ( int i = 0; i < horarios.size(); i ++ ) { 
                Object[] dadosHorario = (Object[]) horarios.get(i);
                Profissional profiss = new Profissional();
                profiss.setId((int) dadosHorario[10]);
                profiss.setNome((String) dadosHorario[11]);
                profissional.addItem(profiss);
                
                if ( profiss.getId() == agendamento.getProfissional().getId() ) {
                    profissional.setSelectedItem(profiss);
                }
            }            

            // Popula combo de clientes.
            Cliente cli = new Cliente();
            cli.setNome(" ");
            cliente.removeAllItems();
            cliente.addItem(cli);
            List<Object> clientes = ClienteDAO.getInstance().findAll(new Cliente());
            for ( Object object : clientes ) { 
                Cliente client = (Cliente) object;
                cliente.addItem(client);
                
                if ( client.getId() == agendamento.getCliente().getId() ) {
                    cliente.setSelectedItem(client);
                }
            }
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
           
            id.setText((agendamento.getId() != 0) ? Integer.toString(agendamento.getId()) : "");
            data.setText(simpleDateFormat.format(agendamento.getDataAgendada()));
            horario.setText(simpleTimeFormat.format(agendamento.getHorarioAgendado()));
            tipoDeAtendimento.setSelectedItem(agendamento.getTipoDeAtendimento());
            observacao.setText(agendamento.getObservacao());
            
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean validarCampos() {
        String erro = "";
        
        try {
            if ( statusAgendamento.getSelectedItem() == null ) {
                erro += "O campo 'Status do agendamento' é requerido!\n";
            }
            
            if ( data.getText().isEmpty() ) {
                erro += "O campo 'Data' é requerido!\n";
            }
            
            if ( profissional.getSelectedItem().toString().equals(" ") ) {
                erro += "O campo 'Profissional' é requerido!\n";
            }
            
            if ( tipoDeAtendimento.getSelectedItem().toString().equals(" ") ) {
                erro += "O campo 'Tipo de atendimento' é requerido!\n";
            }
            
            if ( nomeCliente.getText().isEmpty() && cliente.getSelectedItem().toString().equals(" ") ) {
                erro += "O campo 'Cliente' é requerido!\n";
            }
            
            if ( horario.getText().isEmpty() ) {
                erro += "O campo 'Horário' é requerido!\n";
            }
            
            if ( !erro.isEmpty() ) {
                throw new Exception(erro);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        statusAgendamento = new javax.swing.JComboBox();
        profissional = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipoDeAtendimento = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        horario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacao = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        cliente = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nomeCliente = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        data = new javax.swing.JFormattedTextField();
        telefoneCelularCliente = new javax.swing.JFormattedTextField();

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

        jLabel3.setText("Status do agendamento:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("*");

        id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        profissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profissionalActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de atendimento:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("*");

        jLabel6.setText("Profissional:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("*");

        jLabel9.setText("Data:");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("*");

        jLabel11.setText("Horário:");

        observacao.setColumns(20);
        observacao.setRows(5);
        jScrollPane1.setViewportView(observacao);

        jLabel12.setText("Observação:");

        cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setForeground(java.awt.Color.red);
        jLabel13.setText("*");

        jLabel14.setText("Cliente:");

        jLabel15.setText("Nome do cliente:");

        jLabel16.setText("Telefone celular do cliente:");

        javax.swing.GroupLayout labelsPainelLayout = new javax.swing.GroupLayout(labelsPainel);
        labelsPainel.setLayout(labelsPainelLayout);
        labelsPainelLayout.setHorizontalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusAgendamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoDeAtendimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profissional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomeCliente)
                            .addComponent(horario)
                            .addComponent(jScrollPane1)
                            .addComponent(data)
                            .addComponent(telefoneCelularCliente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(statusAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(profissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(tipoDeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(telefoneCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(90, Short.MAX_VALUE))
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

        jTabbedPane1.addTab("Agendamento", jPanel2);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
                java.util.Date date = simpleDateFormat.parse(data.getText());
                java.util.Date time = simpleTimeFormat.parse(horario.getText());
                
                TipoDeRepeticao tipoderepeticao = new TipoDeRepeticao();
                tipoderepeticao.setId(tipoderepeticao.TIPO_DE_REPETICAO_NENHUMA);
                
                Agendamento agendamento = new Agendamento();
                agendamento.setTipoDeRepeticao(tipoderepeticao);
                if ( id.getText().length() > 0 )
                {
                    agendamento.setId(Integer.parseInt(id.getText()));
                }
                agendamento.setStatusAgendamento((StatusAgendamento) statusAgendamento.getSelectedItem());
                agendamento.setDataAgendada(new java.sql.Date(date.getTime()));
                agendamento.setProfissional((Profissional) profissional.getSelectedItem());
                agendamento.setTipoDeAtendimento((TipoDeAtendimento) tipoDeAtendimento.getSelectedItem());
                
                // Caso não tenha sido selecionado um cliente já cadastrado, registra um novo.
                if ( cliente.getSelectedItem().toString().equals(" ") ) {
                    ClienteFormulario clienteForm = new ClienteFormulario();
                    clienteForm.getNome().setText(nomeCliente.getText());
                    clienteForm.getTelefoneCelular().setText(telefoneCelularCliente.getText());
                    clienteForm.getLogin().setText(nomeCliente.getText().replace(" ", "_").toLowerCase());
                    clienteForm.getSenha().setText(UserAccount.DEFAULT_PASSWORD);
                    agendamento.setCliente(clienteForm.salvarCliente());
                    
                } else {
                    agendamento.setCliente((Cliente) cliente.getSelectedItem());
                }                
                
                agendamento.setHorarioAgendado(new java.sql.Time(time.getTime()));
                agendamento.setObservacao(observacao.getText());

                if ( id.getText().length() > 0 ) {
                    AgendamentoDAO.getInstance().merge(agendamento);
                } else {
                    AgendamentoDAO.getInstance().persist(agendamento);
                }

                parent.atualizarGrid(agendamento.getId(), new ArrayList());
                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);                
                this.setVisible(false);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

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
                    
                    if ( tipoDeAtendimentoDoProfissional.getTipoDeAtendimento().getId() == agendamento.getTipoDeAtendimento().getId() ) {
                        tipoDeAtendimento.setSelectedItem(tipoDeAtendimentoDoProfissional.getTipoDeAtendimento());
                    }
                } 
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_profissionalActionPerformed

    private void clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteActionPerformed
        Cliente cli = (Cliente) cliente.getSelectedItem();
        nomeCliente.setEnabled(true);
        telefoneCelularCliente.setEnabled(true);
        
        if ( !(cli.toString().equals(" ")) ) {
            nomeCliente.setText(null);
            nomeCliente.setEnabled(false);
            telefoneCelularCliente.setText(null);
            telefoneCelularCliente.setEnabled(false);
        }
    }//GEN-LAST:event_clienteActionPerformed

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
            java.util.logging.Logger.getLogger(AgendamentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendamentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendamentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendamentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgendamentoFormulario dialog = new AgendamentoFormulario(parent, true);
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
    private javax.swing.JComboBox cliente;
    private javax.swing.JFormattedTextField data;
    private javax.swing.JTextField horario;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel labelsPainel;
    private javax.swing.JTextField nomeCliente;
    private javax.swing.JTextArea observacao;
    private javax.swing.JComboBox profissional;
    private javax.swing.JComboBox statusAgendamento;
    private javax.swing.JFormattedTextField telefoneCelularCliente;
    private javax.swing.JComboBox tipoDeAtendimento;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
