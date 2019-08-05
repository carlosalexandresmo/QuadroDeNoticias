package br.com.desktop.noticias.visao;

import br.com.desktop.noticias.Main;
import br.com.desktop.noticias.modelo.DimensionaColunas;
import br.com.desktop.noticias.modelo.Noticia;
import br.com.desktop.noticias.modelo.TabelaNoticias;
import br.com.desktop.noticias.modelo.Tema;
import br.com.desktop.noticias.modelodao.NoticiaDao;
import br.com.desktop.noticias.modelodao.TemaDao;
import br.com.desktop.noticias.utilitario.Conversor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ListaNoticias extends javax.swing.JFrame {
    private Noticia noticia;
    private static NoticiaDao noticiaDao;
    private TemaDao temaDao;
    private static TabelaNoticias tabelaNoticias = new TabelaNoticias();
    private DefaultComboBoxModel comboBoxTema;
    List<Tema> temas;
    List<Noticia> lsNoticias;
    
    private JPopupMenu popup;
    private JMenuItem visualizar;
    private JMenuItem alterar;

    public ListaNoticias() {
        initComponents();
        ListaNoticias_config();
        setListeners();
    }
    
    private void ListaNoticias_config(){
        setTitle("Quadro de Notícias - Lista de Notícias");
        setExtendedState(MenuNoticias.MAXIMIZED_BOTH);
        setDefaultCloseOperation(MenuNoticias.DISPOSE_ON_CLOSE);
        noticiaDao = new NoticiaDao();
        temaDao = new TemaDao();
        preencherTemas();
        tblNoticias.setModel(tabelaNoticias);
        tblNoticias.setAutoCreateRowSorter(true);
        
        try {
            atualizaTabela();
            DimensionaColunas.dimensionaTabela(tblNoticias);
            lsNoticias = noticiaDao.retornaLista();
        } catch (Exception ex) {
            Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void atualizaTabela() throws Exception{
        tabelaNoticias.setResultados(noticiaDao.retornaLista());        
    }
    
    private void setListeners(){
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserir();
            }
        });
        
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                List<Noticia> noticias = new LinkedList<Noticia>();
                Date data = dtcData.getDate();   
                if(rdbData.isSelected()){
                    if (data == null) {
                        JOptionPane.showMessageDialog(null, "Informe a Data!");
                        return;
                    }
                    try {
                        noticias = noticiaDao.retornaListaPorData(Conversor.converteDateToStringMysql(data));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                } else if(rdbTema.isSelected()){
                    if (getCbxTemas().getSelectedIndex()==-1) {
                        JOptionPane.showMessageDialog(null, "Selecione um Tema!");
                        return;
                    }
                    try {
                        int intTema = temas.get(getCbxTemas().getSelectedIndex()).getTem_cod();
                        noticias = noticiaDao.retornaListaPorTema(intTema);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(rdbTexto.isSelected()){
                    try {
                        String strConteudo = txtTexto.getText();
                        noticias = noticiaDao.retornaListaPorContTexto(strConteudo);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Selecionar um Opção!","Atenção!",JOptionPane.INFORMATION_MESSAGE);
                }
                
                if(noticias.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum registro Encontrado!","Atenção!",JOptionPane.INFORMATION_MESSAGE);
                } else {
                tabelaNoticias.setResultados(noticias);
                DimensionaColunas.dimensionaTabela(tblNoticias);
                }
            }
        });
        
        
        btnExportarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportar();
            }
        });
        
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizar();
            }
        });
        
        mnuAdicionarNot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserir();
                
            }
        });
        
         mnuAlterarNot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });
         
          mnuExcluirNot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
          
           mnuVisualizarNot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizar();
            }
        });
           
            mnuExportarArquivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportar();
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
    
    private void limpar(){
        cbxTema.setSelectedIndex(0);
        txtTexto.setText("");
    }
    
    private void inserir(){
        new EditaNoticia(null).setVisible(true);
        DimensionaColunas.dimensionaTabela(tblNoticias);
    }
    
    private void alterar(){
        if (tblNoticias.getSelectedRow() >= 0) {
            noticia = tabelaNoticias.getDados(tblNoticias.getSelectedRow());
            
            EditaNoticia edita = null;
            
            edita = new EditaNoticia(noticia);
            edita.getDtcData().setDate(tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_data());
            
            
            try {
                noticiaDao = new NoticiaDao();
                lsNoticias = noticiaDao.retornaLista();
                temas = temaDao.retornaLista();
            } catch (Exception ex) {
                Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cont = 0;
            int contsel = 0;
            for (Tema tema : temas) {
                edita.getCbxTemas().addItem(tema.getTem_nome());
                if (tema.getTem_cod() == (lsNoticias.get(tblNoticias.getSelectedRow()).getTema().getTem_cod())) {
                    contsel = cont;
                }
                cont++;
            }
            edita.getCbxTemas().setSelectedIndex(contsel);
        
            edita.getTxtAssunto().setText(tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_assunto());
            edita.getTextNoticia().setText(tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_texto());

            edita.setVisible(true);
            DimensionaColunas.dimensionaTabela(tblNoticias);
        } else {
            JOptionPane.showMessageDialog(null, "Favor Selecionar uma Linha!", "Mensagem!", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private void excluir(){
        if (tblNoticias.getSelectedRow() >= 0) {
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o Registro?", "Confirma?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                   List<Noticia> noticias = noticiaDao.retornaLista();
                    noticiaDao.excluir(noticias.get(tblNoticias.getSelectedRow()).getNot_cod());
                    atualizaTabela();
                    DimensionaColunas.dimensionaTabela(tblNoticias);
                    JOptionPane.showMessageDialog(null, "Registro excluído com Sucesso!", "Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Favor Selecionar uma Linha!", "Mensagem!", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private void exportar() {
        noticiaDao = new NoticiaDao();
        if (tblNoticias.getSelectedRow() >= 0) {
            try {
                noticiaDao.retornaNoticiaTexto(lsNoticias.get(tblNoticias.getSelectedRow()).getNot_cod());
            } catch (Exception ex) {
                Logger.getLogger(ListaNoticias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecionar um Linha!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public void vizualizar(){
        if (tblNoticias.getSelectedRow() >= 0) {
            noticia = tabelaNoticias.getDados(tblNoticias.getSelectedRow());
            VerNoticia ver = null;
            ver = new VerNoticia(this, true);
            
            ver.getLblData().setText("Data: "+ Conversor.converteDateToString(tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_data()));
            ver.getLblAssunto().setText("Assunto: "+tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_assunto());
            ver.getTextNoticia().setText(tabelaNoticias.getDados(tblNoticias.getSelectedRow()).getNot_texto());
            
            ver.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Favor Selecionar uma Linha!", "Mensagem!", JOptionPane.INFORMATION_MESSAGE);
        }
            
    }
    
    public javax.swing.JComboBox getCbxTemas(){
    return cbxTema;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNoticias = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdbData = new javax.swing.JRadioButton();
        rdbTema = new javax.swing.JRadioButton();
        rdbTexto = new javax.swing.JRadioButton();
        dtcData = new com.toedter.calendar.JDateChooser();
        cbxTema = new javax.swing.JComboBox();
        txtTexto = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnExportarTexto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuTabela = new javax.swing.JMenu();
        mnuAdicionarNot = new javax.swing.JMenuItem();
        mnuAlterarNot = new javax.swing.JMenuItem();
        mnuExcluirNot = new javax.swing.JMenuItem();
        mnuVisualizarNot = new javax.swing.JMenuItem();
        mnuExportarArquivo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuSair = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mnuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblNoticias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblNoticias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNoticiasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNoticias);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar por:"));

        buttonGroup1.add(rdbData);
        rdbData.setText("Data:");

        buttonGroup1.add(rdbTema);
        rdbTema.setText("Tema:");

        buttonGroup1.add(rdbTexto);
        rdbTexto.setText("Conteúdo no Texto:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/magnifier.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtcData, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(rdbTema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTema, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(rdbTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbData)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisar)
                        .addComponent(txtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbTexto)
                        .addComponent(cbxTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdbTema)))
                .addContainerGap())
        );

        jToolBar1.setRollover(true);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar Notícia");
        btnAdicionar.setFocusable(false);
        btnAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAdicionar);

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_edit.png"))); // NOI18N
        btnAlterar.setToolTipText("Modificar Notícia");
        btnAlterar.setFocusable(false);
        btnAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAlterar);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_delete.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir Notícia");
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnExcluir);

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/balloon mini.png"))); // NOI18N
        btnVisualizar.setToolTipText("Visualizar Notícia");
        btnVisualizar.setFocusable(false);
        btnVisualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVisualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnVisualizar);

        btnExportarTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/page_white_text.png"))); // NOI18N
        btnExportarTexto.setToolTipText("Exportar em Texto Puro");
        btnExportarTexto.setFocusable(false);
        btnExportarTexto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportarTexto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnExportarTexto);

        mnuTabela.setText("Tabela");

        mnuAdicionarNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_add.png"))); // NOI18N
        mnuAdicionarNot.setText("Adicionar Notícia");
        mnuTabela.add(mnuAdicionarNot);

        mnuAlterarNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_edit.png"))); // NOI18N
        mnuAlterarNot.setText("Alterar Notícia");
        mnuTabela.add(mnuAlterarNot);

        mnuExcluirNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/script_delete.png"))); // NOI18N
        mnuExcluirNot.setText("Excluir Notícia");
        mnuTabela.add(mnuExcluirNot);

        mnuVisualizarNot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/balloon mini.png"))); // NOI18N
        mnuVisualizarNot.setText("Visualizar Notícia");
        mnuTabela.add(mnuVisualizarNot);

        mnuExportarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/page_white_text.png"))); // NOI18N
        mnuExportarArquivo.setText("Exportar Notícia em Arquivo");
        mnuTabela.add(mnuExportarArquivo);
        mnuTabela.add(jSeparator1);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNoticiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNoticiasMouseClicked
        if (tblNoticias.getSelectedRow() >= 0) {
            if (evt.getClickCount() >= 2) {
                vizualizar();
            } else if (evt.getButton() == MouseEvent.BUTTON3) {
                menu_Popup();
                popup.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblNoticiasMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ListaNoticias().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExportarTexto;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxTema;
    private com.toedter.calendar.JDateChooser dtcData;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mnuAdicionarNot;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenuItem mnuAlterarNot;
    private javax.swing.JMenuItem mnuExcluirNot;
    private javax.swing.JMenuItem mnuExportarArquivo;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenuItem mnuSobre;
    private javax.swing.JMenu mnuTabela;
    private javax.swing.JMenuItem mnuVisualizarNot;
    private javax.swing.JRadioButton rdbData;
    private javax.swing.JRadioButton rdbTema;
    private javax.swing.JRadioButton rdbTexto;
    private javax.swing.JTable tblNoticias;
    private javax.swing.JTextField txtTexto;
    // End of variables declaration//GEN-END:variables
    
    private void preencherTemas() {
        try {
            temas = temaDao.retornaLista();
            comboBoxTema = new DefaultComboBoxModel(temas.toArray());
            getCbxTemas().setModel(comboBoxTema);
            comboBoxTema.setSelectedItem("SELECIONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void menu_Popup() {
        popup = new JPopupMenu();
        visualizar = new JMenuItem("Visualizar Notícia");
        alterar = new JMenuItem("Alterar Notícia");
        
        visualizar.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent ev) {
                        vizualizar();
                    }
                });
        
        alterar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        alterar();
                    }
                });

        popup.add(visualizar);
        popup.add(alterar);
    }

}
