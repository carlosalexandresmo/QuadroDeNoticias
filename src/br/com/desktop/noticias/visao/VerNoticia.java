package br.com.desktop.noticias.visao;

import br.com.desktop.noticias.modelo.Noticia;
import br.com.desktop.noticias.modelodao.NoticiaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class VerNoticia extends javax.swing.JDialog {
    private NoticiaDao noticiaDao;
    List<Noticia> lista_not_sel = new LinkedList<Noticia>();

    public VerNoticia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ListaNoticias_config();
        setListeners();
    }
    
    private void ListaNoticias_config(){
        setTitle("Quadro de Notícias - Visualizar Notícia");
        setDefaultCloseOperation(MenuNoticias.DISPOSE_ON_CLOSE);
        noticiaDao = new NoticiaDao();
        textNoticia.setLineWrap(true);
    }
    
    private void setListeners(){
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        lblAssunto = new javax.swing.JLabel();
        lblNoticia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textNoticia = new javax.swing.JTextArea();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 51, 0)));

        lblData.setText("Data:");

        lblAssunto.setText("Assunto:");

        lblNoticia.setText("Notícia:");

        textNoticia.setBackground(new java.awt.Color(240, 240, 240));
        textNoticia.setColumns(20);
        textNoticia.setEditable(false);
        textNoticia.setRows(5);
        jScrollPane1.setViewportView(textNoticia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblData)
                    .addComponent(lblAssunto)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNoticia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblData)
                .addGap(18, 18, 18)
                .addComponent(lblAssunto)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNoticia)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnFechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnFechar)
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                VerNoticia dialog = new VerNoticia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAssunto;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNoticia;
    private javax.swing.JTextArea textNoticia;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getLblAssunto() {
        return lblAssunto;
    }

    public void setLblAssunto(javax.swing.JLabel lblAssunto) {
        this.lblAssunto = lblAssunto;
    }

    public javax.swing.JLabel getLblData() {
        return lblData;
    }

    public void setLblData(javax.swing.JLabel lblData) {
        this.lblData = lblData;
    }

    public javax.swing.JLabel getLblNoticia() {
        return lblNoticia;
    }

    public void setLblNoticia(javax.swing.JLabel lblNoticia) {
        this.lblNoticia = lblNoticia;
    }

    public javax.swing.JTextArea getTextNoticia() {
        return textNoticia;
    }

    public void setTextNoticia(javax.swing.JTextArea textNoticia) {
        this.textNoticia = textNoticia;
    }
}
