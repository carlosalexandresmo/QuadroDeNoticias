package br.com.desktop.noticias.modelodao;

import br.com.desktop.noticias.controle.Conexao;
import br.com.desktop.noticias.modelo.Noticia;
import br.com.desktop.noticias.utilitario.Conversor;
import br.com.desktop.noticias.utilitario.EscreverArquivo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class NoticiaDao {

    private String erro = "Erro ao ";
    private PreparedStatement ps;

    public NoticiaDao() {
    }

    public void inserir(Noticia noticia) throws Exception {
        try {
            String sql = "INSERT INTO Noticia (not_data, not_assunto, not_desc, tem_cod) VALUES (?, ?, ?, ?)";

            ps = Conexao.openConexao().prepareStatement(sql);

            ps.setString(1, Conversor.converteDateToStringMysql(noticia.getNot_data()));
            ps.setString(2, noticia.getNot_assunto());
            ps.setString(3, noticia.getNot_texto());
            ps.setLong(4, noticia.getTema().getTem_cod());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            String exception = "incluir Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }

    }

    public void alterar(Noticia noticia) throws Exception {
        try {
            String sql = "UPDATE Noticia SET not_data = ?, not_assunto = ?, not_desc = ?, tem_cod = ? WHERE not_cod = ?";

            ps = Conexao.openConexao().prepareStatement(sql);

            ps.setLong(5, noticia.getNot_cod());
            ps.setString(1, Conversor.converteDateToStringMysql(noticia.getNot_data()));
            ps.setString(2, noticia.getNot_assunto());
            ps.setString(3, noticia.getNot_texto());
            ps.setLong(4, noticia.getTema().getTem_cod());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "alterar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
    }

    public void excluir(Long not_cod) throws Exception {
        try {
            String sql = "DELETE FROM Noticia WHERE not_cod = " + not_cod;

            ps = Conexao.openConexao().prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            String exception = "excluir Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
    }

    public List<Noticia> retornaLista() throws Exception {

        List<Noticia> lista = new LinkedList<Noticia>();
        try {
            String sql = "SELECT * FROM Noticia ORDER BY not_data DESC";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                TemaDao temaDao = new TemaDao();

                noticia.setNot_cod(rs.getLong("not_cod"));
                noticia.setNot_data(rs.getDate("not_data"));
                noticia.setNot_assunto(rs.getString("not_assunto"));
                noticia.setNot_texto(rs.getString("not_desc"));
                noticia.setTema(temaDao.retornaObjetoTema(rs.getInt("tem_cod")));
                
                lista.add(noticia);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }
    
    public List<Noticia> retornaListaPorData(String date) throws Exception {

        List<Noticia> lista = new LinkedList<Noticia>();
        try {
            String sql = "SELECT * FROM Noticia WHERE not_data='"+date+"'";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                TemaDao temaDao = new TemaDao();

                noticia.setNot_cod(rs.getLong("not_cod"));
                noticia.setNot_data(rs.getDate("not_data"));
                noticia.setNot_assunto(rs.getString("not_assunto"));
                noticia.setNot_texto(rs.getString("not_desc"));
                noticia.setTema(temaDao.retornaObjetoTema(rs.getInt("tem_cod")));
                
                lista.add(noticia);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }
    
        public List<Noticia> retornaListaPorTema(int tema) throws Exception {

        List<Noticia> lista = new LinkedList<Noticia>();
        try {
            String sql = "SELECT * FROM Noticia WHERE tem_cod='"+tema+"'";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                TemaDao temaDao = new TemaDao();

                noticia.setNot_cod(rs.getLong("not_cod"));
                noticia.setNot_data(rs.getDate("not_data"));
                noticia.setNot_assunto(rs.getString("not_assunto"));
                noticia.setNot_texto(rs.getString("not_desc"));
                noticia.setTema(temaDao.retornaObjetoTema(rs.getInt("tem_cod")));
                
                lista.add(noticia);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }
        
            public List<Noticia> retornaListaPorContTexto(String conteudo) throws Exception {

        List<Noticia> lista = new LinkedList<Noticia>();
        try {
            String sql = "SELECT * FROM Noticia WHERE not_desc like'%"+conteudo+"%'";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                TemaDao temaDao = new TemaDao();

                noticia.setNot_cod(rs.getLong("not_cod"));
                noticia.setNot_data(rs.getDate("not_data"));
                noticia.setNot_assunto(rs.getString("not_assunto"));
                noticia.setNot_texto(rs.getString("not_desc"));
                noticia.setTema(temaDao.retornaObjetoTema(rs.getInt("tem_cod")));
                
                lista.add(noticia);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }
    
    

    public void retornaNoticiaTexto(Long not_cod) throws Exception {

        EscreverArquivo escreverArquivo = new EscreverArquivo();
        try {

            String sql = "SELECT not_data, not_assunto, not_desc FROM Noticia WHERE not_cod=" + not_cod;

            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            String data, assunto, texto_noticia;

            while (rs.next()) {
                data = rs.getString(1);
                assunto = rs.getString(2);
                texto_noticia = rs.getString(3);

                if (!texto_noticia.isEmpty()) {

                    escreverArquivo.escrever(data, assunto, texto_noticia);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "gerar Noticia! ";
            throw new Exception(erro + exception + e.getMessage());
        }
    }
}
