package br.com.desktop.noticias.visao;

import br.com.desktop.noticias.Main;
import br.com.desktop.noticias.modelo.Noticia;
import br.com.desktop.noticias.modelo.Tema;
import br.com.desktop.noticias.modelodao.NoticiaDao;
import br.com.desktop.noticias.modelodao.TemaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class EditaNoticia extends javax.swing.JFrame {
    private Noticia noticia;
    private NoticiaDao noticiaDao;
    private TemaDao temaDao;
    private DefaultComboBoxModel comboBoxTema;
     private List<Tema> lista_tema = new LinkedList<Tema>();
     boolean continua = true;

    public EditaNoticia(Noticia noticia) {
        this.noticia=noticia;
        initComponents();
        EditaNoticia_config();
        setListeners();
    }
    
    public void EditaNoticia_config(){
        setTitle("Quadro de Notícias - Editar Notícia");
        setDefaultCloseOperation(MenuNoticias.DISPOSE_ON_CLOSE);
        noticiaDao = new NoticiaDao();
        temaDao = new TemaDao();
        try {
            lista_tema = temaDao.retornaLista();
        } catch (Exception ex) {
            Logger.getLogger(EditaNoticia.class.getName()).log(Level.SEVERE, null, ex);
        }
        textNoticia.setLineWrap(true);
        if(noticia==null){
            preencherTemas();
        }
    }
    
    public void setListeners() {

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date dtData = dtcData.getDate();
                String strAssunto = txtAssunto.getText().toUpperCase();
                String strTexto = textNoticia.getText().toUpperCase();
                
                verifica(dtData, strAssunto, strTexto);
                
                if (continua) {
                    if (noticia == null) {
                        noticia = new Noticia();

                        noticia.setNot_data(dtData);
                        noticia.setTema(lista_tema.get(getCbxTemas().getSelectedIndex()));
                        noticia.setNot_assunto(strAssunto);
                        noticia.setNot_texto(strTexto);
                        noticia.setNot_data(dtData);

                        if (JOptionPane.showConfirmDialog(null, "Gravar Inclusão da Notícia?", "Confirma?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            try {
                                noticiaDao.inserir(noticia);
                            } catch (Exception ex) {
                                Logger.getLogger(EditaNoticia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Notícia Cadastrado com Sucesso!", "Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        
                        noticia.setNot_data(dtData);
                        noticia.setTema(lista_tema.get(getCbxTemas().getSelectedIndex()));
                        noticia.setNot_assunto(strAssunto);
                        noticia.setNot_texto(strTexto);
                        noticia.setNot_data(dtData);

                        if (JOptionPane.showConfirmDialog(null, "Gravar Alterações dos Notícia?", "Confirma?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            try {
                                noticiaDao.alterar(noticia);
                            } catch (Exception ex) {
                                Logger.getLogger(EditaNoticia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Notícia Alterado com Sucesso!", "Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                            noticia = null;

                        }
                    } 
                    setVisible(false);
                    try {
                        ListaNoticias.atualizaTabela();
                    } catch (Exception ex) {
                        Logger.getLogger(EditaNoticia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        mnuSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sobre();
            }
        });
        
        mnuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
    
    public void limpar(){
        cbxTemas.setSelectedItem("SELECIONE");
        txtAssunto.setText("");
        textNoticia.setText("");
    }
    
    private void verifica(Date data_verificar, String ass, String texto) {
        Date hoje = new Date();

        if (data_verificar == null) {
            JOptionPane.showMessageDialog(null, "Campo Data Vazio!", "Campo Data Vazio!", JOptionPane.ERROR_MESSAGE);
            continua = false;
        } else if (data_verificar.after(hoje)) {
            JOptionPane.showMessageDialog(null, "Não é possível inserir notícia em data futura!", "Erro de Data!", JOptionPane.ERROR_MESSAGE);
            continua = false;
        } else if (ass.isEmpty() || ass == null) {
            JOptionPane.showMessageDialog(null, "Notícia em sem Assunto, favor inserir!", "Campo Assunto Vazio!", JOptionPane.ERROR_MESSAGE);
            continua = false;
            txtAssunto.requestFocus();
        } else if (texto.isEmpty() || texto == null) {
            JOptionPane.showMessageDialog(null, "Sem Notícia, favor inserir o texto!", "Campo Notícia Vazio!", JOptionPane.ERROR_MESSAGE);
            continua = false;
            textNoticia.requestFocus();
        } else if (getCbxTemas().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Favor Selecionar o Tema!", "Campo Tema Não Selecionado!", JOptionPane.ERROR_MESSAGE);
            continua = false;
        } else {
            continua = true;
        }

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxTemas = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtAssunto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textNoticia = new javax.swing.JTextArea();
        dtcData = new com.toedter.calendar.JDateChooser();
        btnCancelar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuTabela = new javax.swing.JMenu();
        mnuSair = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mnuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Edita Notícia"));

        jLabel1.setText("Data:");

        jLabel2.setText("Tema:");

        jLabel3.setText("Assunto:");

        jLabel4.setText("Descrição da Notícia:");

        textNoticia.setColumns(20);
        textNoticia.setRows(5);
        jScrollPane1.setViewportView(textNoticia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtcData, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAssunto, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(dtcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxTemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/broom.png"))); // NOI18N
        btnLimpar.setText("Limpar");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/disk.png"))); // NOI18N
        btnSalvar.setText("Gravar");

        mnuTabela.setText("Tabela");

        mnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cross.png"))); // NOI18N
        mnuSair.setText("Sair...");
        mnuTabela.add(mnuSair);

        jMenuBar1.add(mnuTabela);

        mnuAjuda.setText("Ajuda");

        mnuSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/information.png"))); // NOI18N
        mnuSobre.setText("Sobre...");
        mnuAjuda.add(mnuSobre);

        jMenuBar1.add(mnuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnLimpar, btnSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnLimpar)
                    .addComponent(btnSalvar))
                .addGap(29, 29, 29))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new EditaNoticia(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbxTemas;
    private com.toedter.calendar.JDateChooser dtcData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenuItem mnuSobre;
    private javax.swing.JMenu mnuTabela;
    private javax.swing.JTextArea textNoticia;
    private javax.swing.JTextField txtAssunto;
    // End of variables declaration//GEN-END:variables
    private void preencherTemas() {
        try {
            List<Tema> temas;
            temas = temaDao.retornaLista();
            comboBoxTema = new DefaultComboBoxModel(temas.toArray());
            getCbxTemas().setModel(comboBoxTema);
            comboBoxTema.setSelectedItem("SELECIONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public com.toedter.calendar.JDateChooser getDtcData() {
        return dtcData;
    }

    public void setDtcData(com.toedter.calendar.JDateChooser dtcData) {
        this.dtcData = dtcData;
    }

    public javax.swing.JTextArea getTextNoticia() {
        return textNoticia;
    }

    public void setTextNoticia(javax.swing.JTextArea textNoticia) {
        this.textNoticia = textNoticia;
    }

    public javax.swing.JTextField getTxtAssunto() {
        return txtAssunto;
    }

    public void setTxtAssunto(javax.swing.JTextField txtAssunto) {
        this.txtAssunto = txtAssunto;
    }

    public void setCbxTemas(javax.swing.JComboBox cbxTemas) {
        this.cbxTemas = cbxTemas;
    }
    
    public javax.swing.JComboBox getCbxTemas(){
    return cbxTemas;
}

}
