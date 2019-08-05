package br.com.desktop.noticias.modelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaTemas extends AbstractTableModel {
    
    private final Integer[] vnTamanho = {30};
    private final String[] vsTitulo = {"Temas"};
    private Class[] vsClasses = {String.class};
    private List<Tema> lista_temas = null;
    private List<Object[]> rsTabela = new LinkedList<Object[]>();

    public TabelaTemas() {
        Object[] linha = {""};
        rsTabela.add(linha);
    }

    public void setResultados(List<Tema> listaRes_Tema) {
        lista_temas = listaRes_Tema;
        rsTabela.clear(); 
        if (listaRes_Tema != null) { 
            Iterator<Tema> it = listaRes_Tema.iterator();
            while (it.hasNext()) { 
                Tema tema = it.next();
                Object[] linha = {
                                tema.getTem_nome()};
                rsTabela.add(linha);
            }
        }
        if (listaRes_Tema == null || listaRes_Tema.size() == 0) {
            Object[] linha = {null, null};
            rsTabela.add(linha);
        }
        fireTableStructureChanged();
    }

    public void adicionarLinha(Noticia noticia) {
        Object[] linha = {
                                noticia.getTema().getTem_nome(),
                                noticia.getNot_assunto()};
        rsTabela.add(linha);
        fireTableStructureChanged();
    }

    public void removerLinha(int linha) {
        rsTabela.remove(linha);
        lista_temas.remove(linha);
        fireTableStructureChanged();
    }

    public Tema getDados(int linha) {
        Tema dado = null;
        if (lista_temas != null & lista_temas.size() > linha) {
            dado = lista_temas.get(linha);
        }
        return dado;
    }

    public TabelaTemas(List<Tema> lista) {
        setResultados(lista);
    }
    
    public void limpaTabela(List<Tema> dados){
        lista_temas = dados;
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
