package br.com.desktop.noticias.modelo;

public class Tema {
    
    private int tem_cod;
    private String tem_nome;

    public int getTem_cod() {
        return tem_cod;
    }

    public void setTem_cod(int tem_cod) {
        this.tem_cod = tem_cod;
    }

    public String getTem_nome() {
        return tem_nome;
    }

    public void setTem_nome(String tem_nome) {
        this.tem_nome = tem_nome;
    }
    
    @Override
    public String toString(){
        return tem_nome;
    }
}
