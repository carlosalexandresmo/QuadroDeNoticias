package br.com.desktop.noticias.visao;

import br.com.desktop.noticias.Main;
import br.com.desktop.noticias.modelo.DimensionaColunas;
import br.com.desktop.noticias.modelo.TabelaTemas;
import br.com.desktop.noticias.modelo.Tema;
import br.com.desktop.noticias.modelodao.TemaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EditaTema extends javax.swing.JFrame {
    private Tema tema;
    private TemaDao temaDao;
    private TabelaTemas tabelaTemas = new TabelaTemas();
    boolean novo = true;

    public EditaTema() {
        initComponents();
        EditaTema_config();
        setListeners();
    }
    
    private void EditaTema_config(){
        setTitle("Quadro de Notícias - Editar Tema");
        setDefaultCloseOperation(MenuNoticias.DISPOSE_ON_CLOSE);
        tblTemas.setModel(tabelaTemas);
        temaDao = new TemaDao();
        try {
            atualizaTabela();
            DimensionaColunas.dimensionaTabela(tblTemas);
        } catch (Exception ex) {
            Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtTema.setEditable(false);
    }
    
    private void exibir() {
        if (tema != null) {
            txtTema.setText("" + tema.getTem_nome());
        }
    }
    
    private void mudaEstado() {
        if (novo == true) {
            txtTema.setEditable(true);
            txtTema.requestFocus();
        } else {
            txtTema.setEditable(false);
        }
    }
    
    public void limpar() {
        txtTema.setText("");
    }
    
    private void setListeners(){
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novo = true;
                txtTema.setText("");
                mudaEstado();
            }
        });
        
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblTemas.getSelectedRow() >= 0) {
                    tema = tabelaTemas.getDados(tblTemas.getSelectedRow());
                    exibir();
                    novo = true;
                    mudaEstado();
                } else {
                    JOptionPane.showMessageDialog(null, "Favor Selecionar uma Linha!", "Mensagem!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblTemas.getSelectedRow() >= 0) {

                    if (JOptionPane.showConfirmDialog(null, "Você está prestes a Excluir um Tema Relacionado \na alguma Notícia. Deseja Continuar?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o Tema?", "Confirma?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (opcao == JOptionPane.YES_OPTION) {
                            try {
                                temaDao.excluir(temaDao.retornaLista().get(tblTemas.getSelectedRow()).getTem_cod());
                            } catch (Exception ex) {
                                Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Tema excluído com Sucesso!", "Exclusão!", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                atualizaTabela();
                            } catch (Exception ex) {
                                Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            limpar();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Favor Selecionar uma Linha!", "Mensagem!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strTema = txtTema.getText().toUpperCase();

                if (tema == null) {
                    tema = new Tema();

                    tema.setTem_nome(strTema);

                    if (JOptionPane.showConfirmDialog(null, "Gravar Inclusão do Tema?", "Confirma?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        try {
                            temaDao.inserir(tema);
                            atualizaTabela();
                        } catch (Exception ex) {
                            Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Registro Cadastrado com Sucesso!", "Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                        limpar();
                        novo=false;
                        tema=null;
                        mudaEstado();
                    }

                } else {
                    tema.setTem_nome(strTema);

                    if (JOptionPane.showConfirmDialog(null, "Gravar Alterações do Tema?", "Confirma?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        try {
                            temaDao.alterar(tema);
                            atualizaTabela();
                        } catch (Exception ex) {
                            Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!", "Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                        limpar();
                        novo=false;
                        tema=null;
                        mudaEstado();
                    }
                }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        mnuNovoTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novo = true;
                txtTema.setText("");
                mudaEstado();
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
                dispose();
            }
        });

    }
    
    public void atualizaTabela() throws Exception{
        tabelaTemas.setResultados(temaDao.retornaLista());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTemas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuTabela = new javax.swing.JMenu();
        mnuNovoTema = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mnuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tema de Notícias"));

        tblTemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTemas);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Tema"));

        jLabel1.setText("Nome:");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/disk.png"))); // NOI18N
        btnSalvar.setText("Salvar");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(btnSalvar)
                                .add(18, 18, 18)
                                .add(btnCancelar))
                            .add(txtTema, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {btnCancelar, btnSalvar}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(txtTema, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(33, 33, 33)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnCancelar)
                    .add(btnSalvar))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jToolBar1.setRollover(true);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/add_obj.gif"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar Tema");
        btnAdicionar.setFocusable(false);
        btnAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAdicionar);

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cog.png"))); // NOI18N
        btnAlterar.setToolTipText("Editar Tema");
        btnAlterar.setFocusable(false);
        btnAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAlterar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cross-script.png"))); // NOI18N
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnExcluir);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 281, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        mnuTabela.setText("Tabela");

        mnuNovoTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/blue-folder mini.png"))); // NOI18N
        mnuNovoTema.setText("Novo Tema");
        mnuTabela.add(mnuNovoTema);

        mnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/cross.png"))); // NOI18N
        mnuSair.setText("Sair");
        mnuTabela.add(mnuSair);

        jMenuBar1.add(mnuTabela);

        mnuAjuda.setText("Ajuda");

        mnuSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/information.png"))); // NOI18N
        mnuSobre.setText("Sobre...");
        mnuAjuda.add(mnuSobre);

        jMenuBar1.add(mnuAjuda);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EditaTema().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(EditaTema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenuItem mnuNovoTema;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenuItem mnuSobre;
    private javax.swing.JMenu mnuTabela;
    private javax.swing.JTable tblTemas;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables

}
