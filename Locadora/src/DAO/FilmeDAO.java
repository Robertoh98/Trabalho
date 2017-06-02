package DAO;

import Classes.Filme;
import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FilmeDAO {

    public int salvar(Filme filme) {
        int resultado = -1;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "SELECT * FROM FILME WHERE TITULO = ?";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setString(1, filme.getTitulo());

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("já existe!");
            } else {

                String QUERY_INSERT = "insert into FILME (TITULO,TIPO,QNT) values ( ?, ?, ?)";
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, filme.getTitulo());
                stmt.setInt(2, filme.getTipo());
                stmt.setInt(3, filme.getQuantidade());

                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }

                conn.close();
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;

        }

        return resultado;
    }

    public Filme findOne(int id) {

        Filme filme = new Filme();

        try {

            String QUERY_DETALHE = "select * from FILME where CODIGO_FILME = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                filme = new Filme();
                filme.setCodigo(rs.getInt("CODIGO_FILME"));
                filme.setTitulo(rs.getString("TITULO"));
                filme.setTipo(rs.getInt("TIPO"));
                filme.setQuantidade(rs.getInt("QNT"));
            }
            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            filme = null;

        }

        return filme;
    }

    public int editar(Filme filme) {
        int resultado = -1;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "SELECT * FROM FILME WHERE TITULO = ?";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setString(1, filme.getTitulo());

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("já existe!");
            } else {

                String QUERY_UPDATE = "update FILME set TITULO = ?, TIPO = ?, QNT = ? where CODIGO_FILME = ?";
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, filme.getTitulo());
                stmt.setInt(2, filme.getTipo());
                stmt.setInt(3, filme.getQuantidade());
                stmt.setInt(4, filme.getCodigo());

                stmt.executeUpdate();

                conn.close();
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;

        }

        return resultado;
    }

    public boolean excluir(Filme filme) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "DELETE FROM FILME WHERE CODIGO_FILME = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, filme.getCodigo());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;

    }

    public List<Filme> listar() {
        List<Filme> lista = new ArrayList<Filme>();
        try {
            String QUERY_DETALHE = "SELECT * FROM Filme";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme cliente = new Filme();
                cliente.setCodigo(rs.getInt("CODIGO_FILME"));
                cliente.setTitulo(rs.getString("TITULO"));
                cliente.setTipo(rs.getInt("TIPO"));
                cliente.setQuantidade(rs.getInt("QNT"));
                lista.add(cliente);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return lista;

        }
    }
}
