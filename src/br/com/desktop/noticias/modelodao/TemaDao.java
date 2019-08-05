package br.com.desktop.noticias.modelodao;

import br.com.desktop.noticias.controle.Conexao;
import br.com.desktop.noticias.modelo.Tema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class TemaDao {

    private String erro = "Erro ao ";
    private PreparedStatement ps;

    public TemaDao() {
    }

    public void inserir(Tema tema) throws Exception {
        try {
            String sql = "INSERT INTO Tema (tem_nome) VALUES (?)";

            ps = Conexao.openConexao().prepareStatement(sql);

            ps.setString(1, tema.getTem_nome());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            String exception = "incluir Tema! ";
            throw new Exception(erro + exception + e.getMessage());
        }

    }

    public void alterar(Tema tema) throws Exception {
        try {
            String sql = "UPDATE Tema SET tem_nome = ? WHERE tem_cod = ?";

            ps = Conexao.openConexao().prepareStatement(sql);

            ps.setInt(2, tema.getTem_cod());
            ps.setString(1, tema.getTem_nome());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "alterar Tema! ";
            throw new Exception(erro + exception + e.getMessage());
        }
    }

    public void excluir(int tem_cod) throws Exception {
        try {
            String sql = "DELETE FROM Tema WHERE tem_cod = " + tem_cod;

            ps = Conexao.openConexao().prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            String exception = "excluir Tema! ";
            throw new Exception(erro + exception + e.getMessage());
        }
    }

    public List<Tema> retornaLista() throws Exception {

        List<Tema> lista = new LinkedList<Tema>();
        try {
            String sql;
            sql = "SELECT * FROM Tema";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tema tema = new Tema();

                tema.setTem_cod(rs.getInt(1));
                tema.setTem_nome(rs.getString(2));

                lista.add(tema);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Tema! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }

    public Tema retornaObjetoTema(int tem_cod) throws Exception {

        try {
            String sql;
            sql = "SELECT * FROM Tema WHERE tem_cod = ?";

            ps = Conexao.openConexao().prepareStatement(sql);

            ps.setInt(1, tem_cod);

            ResultSet rs = ps.executeQuery();


            Tema tema = null;
            if (rs.next()) {
                tema = new Tema();

                tema.setTem_cod(rs.getInt(1));
                tema.setTem_nome(rs.getString(2));
            }
            return tema;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    
        public List<Tema> retornaListaCodigos() throws Exception {

        List<Tema> lista = new LinkedList<Tema>();
        try {
            String sql;
            sql = "SELECT tem_cod FROM Tema";
            ps = Conexao.openConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tema tema = new Tema();

                tema.setTem_cod(rs.getInt(1));

                lista.add(tema);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exception = "retornar Códigos da Tabela Tema! ";
            throw new Exception(erro + exception + e.getMessage());
        }
        return lista;
    }
}
