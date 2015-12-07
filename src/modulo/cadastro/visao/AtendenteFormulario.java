/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.cadastro.visao;

import modulo.sistema.visao.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modulo.administrativo.negocio.UserAccount;
import modulo.cadastro.dao.AtendenteDAO;
import modulo.administrativo.dao.UsuarioDAO;
import static modulo.administrativo.visao.UsuarioFormulario.parent;
import modulo.cadastro.dao.CidadeDAO;
import modulo.cadastro.dao.EstadoDAO;
import modulo.cadastro.negocio.Atendente;
import modulo.cadastro.negocio.Cidade;
import modulo.cadastro.negocio.Estado;
import modulo.sistema.negocio.SOptionPane;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author augusto
 */
public class AtendenteFormulario extends javax.swing.JDialog {

    public static AtendenteBusca parent;
    private String message;
    
    /**
     * Creates new form AtendenteFormulario
     */
    public AtendenteFormulario(AtendenteBusca parent, boolean modal) {
        this.parent = parent;
        this.setModal(modal);
        this.setLocation(600, 530);
        initComponents();
        
        try { 
            cpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
            dataNascimento.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            telefoneCelular.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) ####-####")));
            telefoneResidencial.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) ####-####")));
            telefoneTrabalho.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) ####-####")));
        } catch (ParseException ex) {
            Logger.getLogger(AtendenteFormulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ArrayList<Object> estados = new ArrayList<>();
            Estado empty = new Estado();
            empty.setId(0);
            empty.setNome("Selecione um estado...");
            estados.add(empty);
            estados.addAll(EstadoDAO.getInstance().findAll(new Estado()));
            ComboBoxModel model = new DefaultComboBoxModel(estados.toArray());

            estado.setModel(model);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
        botaoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/salvar.png")));
        botaoCancelar.setIcon(new ImageIcon(this.getClass().getResource("/publico/imagens/cancelar.png")));
        
        sexoM.setEnabled(false);
    }
    
    public void popularCampos(Atendente atendente) {
        try {
            id.setText("" + atendente.getId());
            nome.setText(atendente.getNome());
            rg.setText(atendente.getRg());
            cpf.setText(atendente.getCpf());
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            if(atendente.getDataNascimento() != null)
                dataNascimento.setText(format.format(atendente.getDataNascimento()));
            if(atendente.getSexo() == 'M'){
                sexoMActionPerformed(null);
            }
            else{
                sexoFActionPerformed(null);
            }
            observacao.setText(atendente.getObservacao());

            usuario_id.setText("" + atendente.getUsuario().getId());
            login.setText(atendente.getUsuario().getLogin());
            senha.setText("");
            cep.setText(atendente.getCep());
            if(atendente.getCidade() != null){
                estado.setSelectedItem(atendente.getCidade().getEstadoId());
                estadoActionPerformed(null);
                cidade.setSelectedItem(atendente.getCidade());
            }
            bairro.setText(atendente.getBairro());
            endereco.setText(atendente.getEndereco());
            numero.setText(atendente.getNumero() + "");
            complemento.setText(atendente.getComplemento());
            email.setText(atendente.getEmail());
            telefoneCelular.setText(atendente.getTelefoneCelular());
            telefoneResidencial.setText(atendente.getTelefoneResidencial());
            telefoneTrabalho.setText(atendente.getTelefoneTrabalho());

            login.setEditable(false);
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
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        botaoSalvar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelsPainel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rg = new javax.swing.JTextField();
        sexoM = new javax.swing.JRadioButton();
        sexoF = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacao = new javax.swing.JTextArea();
        cpf = new javax.swing.JFormattedTextField();
        dataNascimento = new javax.swing.JFormattedTextField();
        login = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        usuario_id = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pais = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cidade = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        cep = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        bairro = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        endereco = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        complemento = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        telefoneCelular = new javax.swing.JFormattedTextField();
        telefoneResidencial = new javax.swing.JFormattedTextField();
        telefoneTrabalho = new javax.swing.JFormattedTextField();

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

        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("*");

        jLabel3.setText("ID:");

        id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        id.setPreferredSize(new java.awt.Dimension(0, 27));

        jLabel4.setText("RG:");

        sexoM.setBackground(java.awt.SystemColor.controlLtHighlight);
        sexoM.setSelected(true);
        sexoM.setText("Masculino");
        sexoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoMActionPerformed(evt);
            }
        });

        sexoF.setBackground(java.awt.SystemColor.controlLtHighlight);
        sexoF.setText("Feminino");
        sexoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoFActionPerformed(evt);
            }
        });

        jLabel5.setText("Sexo:");

        jLabel6.setText("CPF:");

        jLabel7.setText("Data de nascimento:");

        jLabel9.setText("Login:");

        jLabel21.setText("Observação:");

        observacao.setColumns(20);
        observacao.setRows(5);
        jScrollPane1.setViewportView(observacao);

        cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel26.setForeground(java.awt.Color.red);
        jLabel26.setText("*");

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel27.setForeground(java.awt.Color.red);
        jLabel27.setText("*");

        jLabel10.setText("Senha:");

        jLabel11.setText("ID Usuário:");

        usuario_id.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        usuario_id.setPreferredSize(new java.awt.Dimension(0, 27));

        javax.swing.GroupLayout labelsPainelLayout = new javax.swing.GroupLayout(labelsPainel);
        labelsPainel.setLayout(labelsPainelLayout);
        labelsPainelLayout.setHorizontalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(labelsPainelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(labelsPainelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sexoM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sexoF))
                    .addGroup(labelsPainelLayout.createSequentialGroup()
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(senha)
                            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)))
                    .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, labelsPainelLayout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(usuario_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(labelsPainelLayout.createSequentialGroup()
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                        .addGroup(labelsPainelLayout.createSequentialGroup()
                            .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dataNascimento)
                                .addComponent(cpf)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        labelsPainelLayout.setVerticalGroup(
            labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelsPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexoM)
                    .addComponent(sexoF)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usuario_id, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(labelsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel10))
                .addContainerGap(76, Short.MAX_VALUE))
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

        jTabbedPane1.addTab("Dados pessoais", jPanel2);

        jPanel3.setBackground(java.awt.SystemColor.controlLtHighlight);

        jLabel12.setText("País:");

        pais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Brasil" }));

        jLabel13.setText("Estado:");

        estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione estado..." }));
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });

        jLabel14.setText("Cidade:");

        cidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidadeActionPerformed(evt);
            }
        });

        jLabel15.setText("Cep:");

        jLabel16.setText("Bairro:");

        jLabel17.setText("Endereço:");

        jLabel18.setText("Número:");

        jLabel19.setText("Complemento:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
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

        jTabbedPane1.addTab("Endereço", jPanel1);

        jPanel5.setBackground(java.awt.SystemColor.controlLtHighlight);

        jLabel20.setText("E-mail:");

        jLabel22.setText("Telefone celular:");

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel23.setForeground(java.awt.Color.red);
        jLabel23.setText("*");

        jLabel24.setText("Telefone residencial:");

        jLabel25.setText("Telefone de trabalho:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(telefoneResidencial, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(telefoneCelular)
                            .addComponent(telefoneTrabalho, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(telefoneCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(telefoneResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(telefoneTrabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(329, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contato", jPanel4);

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

    private boolean verificaCampos(){
        boolean ok = true;
        message = "Os seguintes erros ocorreram:\n\n";
        if(nome.getText().isEmpty()){
            ok = false;
            message += "* Campo Nome deve ser preenchido\n";
        }
        if(login.getText().isEmpty()){
            ok = false;
            message += "* Campo Login deve ser preenchido\n";
        }
        if(id.getText().isEmpty()){
            if(senha.getText().isEmpty()){
                ok = false;
                message += "* Campo Senha deve ser preenchido\n";
            }
            else {
                if(senha.getText().length() < 8){
                    ok = false;
                    message += "* Campo Senha deve ter no mínimo 8 caracteres\n";
                }
            }
        }
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        java.util.Date date = new Date((long) 0);
        try {
            if(!dataNascimento.getText().trim().startsWith("/")){
                date = format.parse(dataNascimento.getText());
                java.util.Date hoje = new java.util.Date();

                if(date.after(hoje)){
                    ok = false;
                    message += "* Data inválida\n";
                }
            }
        } catch (ParseException ex) {
            ok = false;
            message += "* Data inválida\n";
        }
        try{
            if(!numero.getText().isEmpty())
                Integer.parseInt(numero.getText());
        }
        catch(NumberFormatException e){
            ok = false;
            message += "* Numero do endereço inválido\n";
        }
        String celular = telefoneCelular.getText().replace("(", "").replace(") ", "").replace("-", "").replace(" ", "");
        if (celular.isEmpty()) {
            ok = false;
            message += "* Campo Telefone Celular deve ser preenchido\n";
        }
        
        return ok;
    }
    
    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        try {
            System.out.println("Saving data....");
            if (verificaCampos())
            {
                Atendente atendente = new Atendente();

                if ( id.getText().length() > 0 )
                {
                    atendente.setId(Integer.parseInt(id.getText()));
                }
                
                atendente.setNome(nome.getText());
                atendente.setRg(rg.getText());
                atendente.setCpf(cpf.getText());

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
                    java.sql.Date data = new java.sql.Date(format.parse(dataNascimento.getText()).getTime());
                    atendente.setDataNascimento(data);
                } catch (ParseException ex) {
                }

                atendente.setSexo(sexoM.isSelected()?'M':'F');
                atendente.setObservacao(observacao.getText());
                try{
                    atendente.setCidade((Cidade) cidade.getSelectedItem());
                }
                catch(ClassCastException e){
                }
                atendente.setCep(cep.getText());
                atendente.setBairro(bairro.getText());
                atendente.setEndereco(endereco.getText());
                atendente.setComplemento(complemento.getText());
                try{
                    atendente.setNumero(Integer.parseInt(numero.getText()));
                }
                catch(NumberFormatException e){
                    atendente.setNumero(0);
                }
                atendente.setTelefoneCelular(telefoneCelular.getText());
                atendente.setTelefoneResidencial(telefoneResidencial.getText());
                String residencial = telefoneResidencial.getText().replace("(", "").replace(") ", "").replace("-", "").replace(" ", "");
                if ( residencial.isEmpty() ) {
                    atendente.setTelefoneResidencial(residencial);
                }
                
                atendente.setTelefoneTrabalho(telefoneTrabalho.getText());
                String trabalho = telefoneTrabalho.getText().replace("(", "").replace(") ", "").replace("-", "").replace(" ", "");
                if ( trabalho.isEmpty() ) {
                    atendente.setTelefoneTrabalho(trabalho);
                }
                atendente.setEmail(email.getText());

                UserAccount usuario = new UserAccount();

                if ( usuario_id.getText().length() > 0 )
                {
                    usuario.setId(Integer.parseInt(usuario_id.getText()));
                }

                usuario.setActive(true);
                usuario.setLogin(login.getText());
                usuario.setPassword(senha.getText());
                usuario.setName(nome.getText());
                atendente.setUsuario(usuario);
                atendente.setTipo("atendente");

                if ( id.getText().length() > 0 ) {
                    AtendenteDAO.getInstance().merge(atendente);
                } else {
                    AtendenteDAO.getInstance().persist(atendente);
                }

                parent.atualizarGrid(atendente.getId(), new ArrayList());
                JOptionPane.showMessageDialog(this, "Registro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                
                this.setVisible(false);
            }
            else
            {
                throw new Exception(message);
            }
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfActionPerformed

    private void sexoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoFActionPerformed
        sexoM.setSelected(false);
        sexoM.setEnabled(true);
        sexoF.setSelected(true);
        sexoF.setEnabled(false);
    }//GEN-LAST:event_sexoFActionPerformed

    private void sexoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoMActionPerformed
        sexoF.setSelected(false);
        sexoF.setEnabled(true);
        sexoM.setSelected(true);
        sexoM.setEnabled(false);
    }//GEN-LAST:event_sexoMActionPerformed

    private void cidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidadeActionPerformed
        
    }//GEN-LAST:event_cidadeActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        try {
            int busca = ((Estado)estado.getSelectedItem()).getId();

            Disjunction or = Restrictions.disjunction();
            or.add(Restrictions.eq("estado_id.id", busca));

            List<Object> grupos = CidadeDAO.getInstance().findByCriteria(new Cidade(), Restrictions.conjunction(), or);
            ComboBoxModel model = new DefaultComboBoxModel(grupos.toArray());
            cidade.setModel(model);
        } catch (Exception err) {
            SOptionPane.showMessageDialog(this, err, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_estadoActionPerformed

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
            java.util.logging.Logger.getLogger(AtendenteFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtendenteFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtendenteFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtendenteFormulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AtendenteFormulario dialog = new AtendenteFormulario(parent, true);
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
    private javax.swing.JTextField bairro;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField cep;
    private javax.swing.JComboBox cidade;
    private javax.swing.JTextField complemento;
    private javax.swing.JFormattedTextField cpf;
    private javax.swing.JFormattedTextField dataNascimento;
    private javax.swing.JTextField email;
    private javax.swing.JTextField endereco;
    private javax.swing.JComboBox estado;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel labelsPainel;
    private javax.swing.JTextField login;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField numero;
    private javax.swing.JTextArea observacao;
    private javax.swing.JComboBox pais;
    private javax.swing.JTextField rg;
    private javax.swing.JPasswordField senha;
    private javax.swing.JRadioButton sexoF;
    private javax.swing.JRadioButton sexoM;
    private javax.swing.JFormattedTextField telefoneCelular;
    private javax.swing.JFormattedTextField telefoneResidencial;
    private javax.swing.JFormattedTextField telefoneTrabalho;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JLabel usuario_id;
    // End of variables declaration//GEN-END:variables
}
