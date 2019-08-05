package br.com.desktop.noticias.modelo;

import br.com.desktop.noticias.utilitario.Conversor;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaNoticias extends AbstractTableModel {
    
    private final Integer[] vnTamanho = {20,60};
    private final String[] vsTitulo = {"Data","Tema","Assunto"};
    private Class[] vsClasses = {String.class,String.class, String.class};
    private List<Noticia> lista_noticias = null;
    private List<Object[]> rsTabela = new LinkedList<Object[]>();

    public TabelaNoticias() {
        Object[] linha = {"","",""};
        rsTabela.add(linha);
    }

    public void setResultados(List<Noticia> listaRes_Noticias) {
        lista_noticias = listaRes_Noticias;
        rsTabela.clear(); 
        if (listaRes_Noticias != null) { 
            Iterator<Noticia> it = listaRes_Noticias.iterator();
            while (it.hasNext()) { 
                Noticia noticia = it.next();
                Object[] linha = {
                                Conversor.converteDateToString(noticia.getNot_data()),
                                noticia.getTema(),
                                noticia.getNot_assunto()};
                rsTabela.add(linha);
            }
        }
        if (listaRes_Noticias == null || listaRes_Noticias.size() == 0) {
            Object[] linha = {null, null};
            rsTabela.add(linha);
        }
        fireTableStructureChanged();
    }

    public void adicionarLinha(Noticia noticia) {
        Object[] linha = {
                                Conversor.converteDateToString(noticia.getNot_data()),
                                noticia.getTema(),
                                noticia.getNot_assunto()};
        rsTabela.add(linha);
        fireTableStructureChanged();
    }

    public void removerLinha(int linha) {
        rsTabela.remove(linha);
        lista_noticias.remove(linha);
        fireTableStructureChanged();
    }

    public Noticia getDados(int linha) {
        Noticia dado = null;
        if (lista_noticias != null & lista_noticias.size() > linha) {
            dado = lista_noticias.get(linha);
        }
        return dado;
    }

    public TabelaNoticias(List<Noticia> lista) {
        setResultados(lista);
    }
    
    public void limpaTabela(List<Noticia> dados){
        lista_noticias = dados;
        rsTabela.clear();
    }

     @Override
    public int getRowCount() {
        //número de linhas
        return rsTabela.size();
    }

    @Override
    public int getColumnCount() {
        //número de colunas
        return vsTitulo.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rsTabela.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return vsTitulo[columnIndex];
    }

    public int getColumnSize(int columnSize) {
        return vnTamanho[columnSize];
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return vsClasses [columnIndex];
    }   
}
