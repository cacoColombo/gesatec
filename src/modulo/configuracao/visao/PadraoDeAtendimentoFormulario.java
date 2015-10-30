/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.configuracao.visao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modulo.configuracao.dao.DiaDaSemanaDAO;
import modulo.configuracao.dao.PadraoDeAtendimentoDAO;
import modulo.configuracao.negocio.DiaDaSemana;
import modulo.configuracao.negocio.PadraoDeAtendimento;
import modulo.sistema.negocio.SOptionPane;

/**
 *
 * @author augusto
 */
public class PadraoDeAtendimentoFormulario extends javax.swing.JDialog {

    public static PadraoDeAtendimentoBusca parent;
    
    /**
     * Creates new form AtendenteFormulario
     * @param parent
     */
    public PadraoDeAtendimentoFormulario(PadraoDeAtendimentoBusca parent, boolean modal) {
        this.parent = parent;
        this.setModal(modal);
        this.setLocation(600, 530);
        initComponents();
        
         MaskFormatter msk = null;
        try { 
            horarioInicioExpediente.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##:##")));
            horarioFimExpediente.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##:##")));
        } catch (ParseException ex) {
            Logger.getLogger(PadraoDeAtendimentoFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ArrayList<Object> diasDaSemana = new ArrayList<>();
            DiaDaSemana empty = new DiaDaSemana();
            empty.setId(0);
            empty.setNome("Selecione um dia da semana...");
            diasDaSemana.add(empty);
            diasDaSemana.addAll(DiaDaSemanaDAO.getInstance().findAll(new DiaDaSemana()));
            ComboBoxModel model = new DefaultComboBoxModel(diasDaSemana.toArray());

            diaDaSemana.setModel(model);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
        botaoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/salvar.png")));
        botaoCancelar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cancelar.png")));
    }
    
    public void popularCampos(PadraoDeAtendimento padraoDeAtendimento) {
        try {
            id.setText(Integer.toString(padraoDeAtendimento.getId()));
            nome.setText(padraoDeAtendimento.getNome());
            diaDaSemana.setSelectedItem(padraoDeAtendimento.getDiaDaSemana());
            horarioInicioExpediente.setText(padraoDeAtendimento.getHorarioInicioExpediente().toString());
            horarioFimExpediente.setText(padraoDeAtendimento.getHorarioFimExpediente().toString());
            tempoConsulta.setText(padraoDeAtendimento.getTempoMedioConsulta() + "");
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean validarCampos() {
        try {
            if ( !(nome.getText().length() > 0) ) {
                throw new Exception("O campo 'Nome' é requerido!");
            }
            
            if ( diaDaSemana.getSelectedIndex() == 0 ) {
                throw new Exception("O dia da semana deve ser selecionado");
            }
            
            DateFormat fmt = new SimpleDateFormat("hh:mm"); 
            try{
                long ms = fmt.parse(horarioInicioExpediente.getText()).getTime(); 
            }
            catch(Exception e){
                throw new Exception("Formato incorreto para horário de início do expediente");
            }
            
            try{
                long ms = fmt.parse(horarioFimExpediente.getText()).getTime();  
            }
            catch(Exception e){
                throw new Exception("Formato incorreto para horário de fim do expediente");
            }
            
            if(tempoConsulta.getText().isEmpty()){
                throw new Exception("O campo 'Tempo Médio de Consulta' é requerido!");
            }
            else{
                try{
                    Integer.parseInt(tempoConsulta.getText());
                }
                catch(Exception err){
                    throw new Exception("Valor inválido para o campo 'Tempo Médio de Consulta'!");
                }
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

        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        toolbar = new javax.swing.JToolBar();
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        painel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        diaDaSemana = new javax.swing.JComboBox();
        horarioInicioExpediente = new javax.swing.JFormattedTextField();
        horarioFimExpediente = new javax.swing.JFormattedTextField();
        tempoConsulta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("*");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setForeground(java.awt.Color.red);
        jLabel12.setText("*");

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

        painel.setBackground(java.awt.SystemColor.controlLtHighlight);

        jLabel1.setText("ID:");

        jLabel3.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("*");

        id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel2.setText("Dia da Semana:");

        jLabel5.setText("Horário Inicial do Expediente:");

        jLabel6.setText("Horário Final do Expediente:");

        jLabel7.setText("Tempo Médio de Consulta:");

        diaDaSemana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        horarioInicioExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioInicioExpedienteActionPerformed(evt);
            }
        });

        tempoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoConsultaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("*");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("*");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setText("*");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setForeground(java.awt.Color.red);
        jLabel13.setText("*");

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tempoConsulta)
                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(diaDaSemana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(horarioInicioExpediente)
                            .addComponent(horarioFimExpediente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel10)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaDaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horarioInicioExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horarioFimExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Certificação", jPanel2);

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
                PadraoDeAtendimento padraoDeAtendimento = new PadraoDeAtendimento();
                if ( id.getText().length() > 0 )
                {
                    padraoDeAtendimento.setId(Integer.parseInt(id.getText()));
                }
                padraoDeAtendimento.setNome(nome.getText());
                padraoDeAtendimento.setDiaDaSemana((DiaDaSemana)diaDaSemana.getSelectedItem());

                DateFormat fmt = new SimpleDateFormat("hh:mm");
                try{
                    long ms = fmt.parse(horarioInicioExpediente.getText()).getTime();
                    padraoDeAtendimento.setHorarioInicioExpediente(new java.sql.Time(ms));
                    ms = fmt.parse(horarioFimExpediente.getText()).getTime();
                    padraoDeAtendimento.setHorarioFimExpediente(new java.sql.Time(ms));
                }
                catch(ParseException err){
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

                padraoDeAtendimento.setTempoMedioConsulta(Integer.parseInt(tempoConsulta.getText()));

                if ( id.getText().length() > 0 ) {
                    PadraoDeAtendimentoDAO.getInstance().merge(padraoDeAtendimento);
                } else {
                    PadraoDeAtendimentoDAO.getInstance().persist(padraoDeAtendimento);
                }

                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                List<Object> registro = new ArrayList();
                registro.add(padraoDeAtendimento);

                parent.atualizarGrid(padraoDeAtendimento.getId(), registro);
                this.setVisible(false);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void horarioInicioExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horarioInicioExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horarioInicioExpedienteActionPerformed

    private void tempoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempoConsultaActionPerformed

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
            java.util.logging.Logger.getLogger(PadraoDeAtendimentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PadraoDeAtendimentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PadraoDeAtendimentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PadraoDeAtendimentoFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PadraoDeAtendimentoFormulario dialog = new PadraoDeAtendimentoFormulario(parent, true);
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
    private javax.swing.JComboBox diaDaSemana;
    private javax.swing.JFormattedTextField horarioFimExpediente;
    private javax.swing.JFormattedTextField horarioInicioExpediente;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JPanel painel;
    private javax.swing.JTextField tempoConsulta;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
