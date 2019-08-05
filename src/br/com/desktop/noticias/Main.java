package br.com.desktop.noticias;

import br.com.desktop.noticias.visao.MenuNoticias;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    private static MenuNoticias menuNoticias;   
    public static void main(String[] args){
        temas();
        menuNoticias = new MenuNoticias();
        menuNoticias.setVisible(true);
    }
    
    private static void temas(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void sobre(){
        JOptionPane.showMessageDialog(null, "Quadro de Notícias\n"
                + "de 16/06/2012\n\n"
                + "Desenvolvido por: \nCarlos Alexandre\n",
                "Sobre Quadro de Notícias", JOptionPane.INFORMATION_MESSAGE, null);
    }
    
}
