package DAO;

import Classes.Aluga;
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

            String QUERY_SELECT = "SELECT * FROM FILME WHERE CODIGO_FILME = ?";

            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setInt(1, aluga.getFilme().getCodigo());

            ResultSet rs = stmt.executeQuery();

            if (rs.getInt("QNT") > 0) {

                String QUERY_INSERT = "INSERT INTO ALUGA (CODIGO_CLIENTE, CODIGO_FILME, DT_EMPRESTIMO) VALUES (?, ?, ?)";

                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, aluga.getCliente().getCodigo());
                stmt.setInt(2, aluga.getFilme().getCodigo());
                stmt.setDate(3, aluga.getDtEmprestimo());

                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();

                String QUERY_UPDATE = "UPDATE FILME SET QNT = QNT - 1 WHERE CODIGO_FILME = ?";
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setInt(1, aluga.getFilme().getCodigo());
                
                stmt.executeUpdate();

                conn.close();
            } else {
                System.out.println("Não temos o produto em estoque");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void devolução(Aluga aluga) {
        
        try{
        PreparedStatement stmt = null;
        Connection conn = ConnectionManager.getConnection();
        
        String QUERY_UPDATE = "UPDATE ALUGA SET DT_DEVOLUCAO = ? WHERE CODIGO_ALUGA = ?";
        
        stmt = conn.prepareStatement(QUERY_UPDATE);
        stmt.setDate(1, aluga.getDtDevolucao());
        stmt.setInt(2, aluga.getCodigo());
        
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
}
