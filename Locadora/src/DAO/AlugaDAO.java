package DAO;

import Classes.Aluga;
import Classes.Cliente;
import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlugaDAO {

    public void alugar(Aluga aluga) {
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "INSERT INTO ALUGA (CODIGO_CLIENTE, CODIGO_FILME, DT_EMPRESTIMO) VALUES (?, ?, now())";

            stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, aluga.getCliente().getCodigo());
            stmt.setInt(2, aluga.getFilme().getCodigo());

            stmt.executeUpdate();

            String QUERY_UPDATE = "UPDATE FILME SET QNT = QNT - 1 WHERE CODIGO_FILME = ?";
            stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setInt(1, aluga.getFilme().getCodigo());

            stmt.executeUpdate();

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void devolução(Aluga aluga) {

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_UPDATE = "UPDATE ALUGA SET DT_DEVOLUCAO = now() WHERE CODIGO_CLIENTE = ? AND CODIGO_FILME = ? AND DT_DEVOLUCAO IS NULL";

            stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setInt(1, aluga.getCliente().getCodigo());
            stmt.setInt(2, aluga.getFilme().getCodigo());

            stmt.executeUpdate();

            String QUERY_ESTOQUE = "UPDATE FILME SET QNT = QNT + 1 WHERE CODIGO_FILME = ?";

            stmt = conn.prepareStatement(QUERY_ESTOQUE);
            stmt.setInt(1, aluga.getFilme().getCodigo());

            stmt.executeUpdate();

            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Aluga findOne(int cliente,int filme) {
        ClienteDAO clienteDao = new ClienteDAO();
        FilmeDAO filmeDao = new FilmeDAO();
        Aluga aluga = new Aluga();
        
        try{
        PreparedStatement stmt = null;
        Connection conn = ConnectionManager.getConnection();
        ResultSet rs = null;

        String Query_findOne = "SELECT*FROM ALUGA WHERE CODIGO_CLIENTE = ? AND CODIGO_FILME = ? AND DT_DEVOLUCAO IS NULL";

        stmt = conn.prepareStatement(Query_findOne);
        stmt.setInt(1, cliente);
        stmt.setInt(2, filme);        
        
        rs = stmt.executeQuery();
        
            while (rs.next()) {                
                aluga.setDtEmprestimo(rs.getDate("DT_EMPRESTIMO"));
                aluga.setDtDevolucao(rs.getDate("DT_DEVOLUCAO"));
                int idCliente = rs.getInt("CODIGO_CLIENTE");
                int idFilme = rs.getInt("CODIGO_FILME");
                aluga.setCliente(clienteDao.findOne(idCliente));
                aluga.setFilme(filmeDao.findOne(idFilme));
            }
        conn.close();
        }catch(Exception e){
            aluga = null;
        }
        return aluga;
    }
}
