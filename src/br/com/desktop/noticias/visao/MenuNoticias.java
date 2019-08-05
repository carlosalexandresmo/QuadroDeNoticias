package br.com.desktop.noticias.visao;

import br.com.desktop.noticias.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNoticias extends javax.swing.JFrame {

    public MenuNoticias() {
        initComponents();
        MenuNoticias_config();
        setListeners();
    }
    
    private void MenuNoticias_config(){
        setTitle("Quadro de Notícias - Menu Inicial");
        setExtendedState(MenuNoticias.MAXIMIZED_BOTH);
        setDefaultCloseOperation(MenuNoticias.EXIT_ON_CLOSE);
    }
    
    private void setListeners(){
        btnNovaNoticia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditaNoticia(null).setVisible(true);
            }
        });
        
        btnNoticias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaNoticias().setVisible(true);
            }
        });
        
        btnAdicionarTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditaTema().setVisible(true);
            }
        });
        
        mnuItemSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sobre();
            }
        });
        
        mnuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
        
        mnuNovaNoticia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditaNoticia(null).setVisible(true);
            }
        });
        
        mnuUltimasNoticias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaNoticias().setVisible(true);
            }
        });
        
        mnuNovoTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditaTema().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnNovaNoticia = new javax.swing.JButton();
        btnAdicionarTema = new javax.swing.JButton();
        btnNoticias = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuNoticia = new javax.swing.JMenu();
        mnuNovaNoticia = new javax.swing.JMenuItem();
        mnuUltimasNoticias = new javax.swing.JMenuItem();
        mnuTema = new javax.swing.JMenu();
        mnuNovoTema = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuSair = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mnuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnNovaNoticia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/document-text.png"))); // NOI18N
        btnNovaNoticia.setToolTipText("Adicionar Nova Notícia");
        btnNovaNoticia.setFocusable(false);
        btnNovaNoticia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovaNoticia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnNovaNoticia);

        btnAdicionarTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/blue-folder.png"))); // NOI18N
        btnAdicionarTema.setToolTipText("Novo Tema");
        btnAdicionarTema.setFocusable(false);
        btnAdicionarTema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionarTema.setName(""); // NOI18N
        btnAdicionarTema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAdicionarTema);

        btnNoticias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/balloon.png"))); // NOI18N
        btnNoticias.setToolTipText("Quadro de Notícias");
        btnNoticias.setFocusable(false);
        btnNoticias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNoticias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnNoticias);

        jMenu1.setText("Tabelas");

        mnuNoticia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/page_white_text.png"))); // NOI18N
        mnuNoticia.setText("Notícia");

        mnuNovaNoticia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/balloon--plus.png"))); // NOI18N
        mnuNovaNoticia.setText("Nova Notícia");
        mnuNoticia.add(mnuNovaNoticia);

        mnuUltimasNoticias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/balloons.png"))); // NOI18N
        mnuUltimasNoticias.setText("Ultimas Notícias");
        mnuNoticia.add(mnuUltimasNoticias);

        jMenu1.add(mnuNoticia);

        mnuTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/blue-folder mini.png"))); // NOI18N
        mnuTema.setText("Tema");

        mnuNovoTema.setText("Novo Tema");
        mnuTema.add(mnuNovoTema);

        jMenu1.add(mnuTema);
        jMenu1.add(jSeparator1);

        mnuSair.setText("Sair");
        jMenu1.add(mnuSair);

        jMenuBar1.add(jMenu1);

        mnuAjuda.setText("Ajuda");

        mnuItemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/desktop/noticias/icones/information.png"))); // NOI18N
        mnuItemSobre.setText("Sobre...");
        mnuAjuda.add(mnuItemSobre);

        jMenuBar1.add(mnuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuNoticias().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarTema;
    private javax.swing.JButton btnNoticias;
    private javax.swing.JButton btnNovaNoticia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenuItem mnuItemSobre;
    private javax.swing.JMenu mnuNoticia;
    private javax.swing.JMenuItem mnuNovaNoticia;
    private javax.swing.JMenuItem mnuNovoTema;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenu mnuTema;
    private javax.swing.JMenuItem mnuUltimasNoticias;
    // End of variables declaration//GEN-END:variables
}
