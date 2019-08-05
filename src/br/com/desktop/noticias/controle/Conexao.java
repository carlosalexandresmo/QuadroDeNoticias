package br.com.desktop.noticias.controle;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Connection con;
    final private static String driverMySQL = "com.mysql.jdbc.Driver";//MySQL
    private static String proxy = "127.0.0.1";
    private static String porta = "3306";
    private static String nomeBanco = "dbnoticiario";
    public static String usuario = "root";
    public static String senha = "";
    public static String urlbanco = "jdbc:mysql://" + proxy + ":" + porta + "/" + nomeBanco;

    public static Connection openConexao() throws Exception {
        try {
            if (con == null) {
                Class.forName(driverMySQL);
                con = DriverManager.getConnection(urlbanco, usuario, senha);
                System.out.println("Conectado");
            } 
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro de Conexão: " + e.getMessage());
        }
        return con;
    }

}
