package br.com.desktop.noticias.modelo;

import java.util.Date;

public class Noticia {
    
    private Long not_cod;
    private Date not_data;
    private String not_assunto;
    private String not_texto;
    private Tema tema;

    public String getNot_assunto() {
        return not_assunto;
    }

    public void setNot_assunto(String not_assunto) {
        this.not_assunto = not_assunto;
    }

    public Long getNot_cod() {
        return not_cod;
    }

    public void setNot_cod(Long not_cod) {
        this.not_cod = not_cod;
    }

    public Date getNot_data() {
        return not_data;
    }

    public void setNot_data(Date not_data) {
        this.not_data = not_data;
    }

    public String getNot_texto() {
        return not_texto;
    }

    public void setNot_texto(String not_texto) {
        this.not_texto = not_texto;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    @Override
    public String toString(){
        return not_texto;
    }
}
