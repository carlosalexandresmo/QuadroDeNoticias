package br.com.desktop.noticias.utilitario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Carlos Alexandre
 */
public class Conversor {

    public static String converteDateToString(Date dataTrab) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dateToString = formato.format(dataTrab);
        return dateToString;
    }

    public static String converteDateToStringMysql(Date dataTrab) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = formato.format(dataTrab);
        return dateToString;
    }

    public static Date converteStringToDate(String dataTrab) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = (Date) formatter.parse(dataTrab);
        return date;
    }
}
