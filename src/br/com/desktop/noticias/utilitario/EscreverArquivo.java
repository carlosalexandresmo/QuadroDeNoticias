package br.com.desktop.noticias.utilitario;

import br.com.epdvr.util.string.StringUtil;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EscreverArquivo {

    String user = System.getProperty("user.home");
    String desktop = user + "\\Desktop\\";

    public void escrever(String linha1, String linha2, String linha3) {
        PrintStream escrever = null;
        try {
            escrever = new PrintStream(new File(desktop + "Noticias.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscreverArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        escrever.println("                          Quadro de Notícias                          ");
        escrever.println();
        linha1 = "Data: " + linha1;
        linha2 = "Assunto:  " + linha2 + " ";

        escrever.println(StringUtil.rightFill(linha1, ' ', 200));
        escrever.println(StringUtil.rightFill(linha2, ' ', 200));
        escrever.println("Descrição da Notícia:");
        escrever.println(StringUtil.rightFill(linha3, ' ', 200));
        
        JOptionPane.showMessageDialog(null, "Arquivo Gerado na Área de Trabalho!", "Arquivo Gerado!", JOptionPane.PLAIN_MESSAGE);
    }
}
