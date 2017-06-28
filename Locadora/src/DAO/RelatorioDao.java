package DAO;

import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RelatorioDao {

    public int numeroClientes() {
        int total = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "SELECT count(CODIGO_CLIENTE) as numeroClientes FROM CLIENTE";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);

            rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("numeroClientes");
            }

            conn.close();

        } catch (Exception ex) {
            total = 0;
        }
        return total;
    }

    public int numeroFilmes() {
        int total = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "SELECT count(CODIGO_FILME) as filmes FROM FILME";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);

            rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("filmes");
            }

            conn.close();

        } catch (Exception ex) {
            total = 0;
        }
        return total;
    }

    public int locacoesMensal() {
        Date date = new Date();                               
        int total = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select count(CODIGO_CLIENTE) as total from aluga where month(DT_EMPRESTIMO) = ?";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setInt(1, date.getMonth()+1);

            rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("total");
            }

            conn.close();

        } catch (Exception ex) {
            total = 0;
        }
        return total;
    }
    
    public int filmes24() {                             
        int total = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select count(CODIGO_FILME) as total from filme where TIPO = 0";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("total");
            }

            conn.close();

        } catch (Exception ex) {
            total = 0;
        }
        return total;
    }
    
    public int filmes48() {                             
        int total = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select count(CODIGO_FILME) as total from filme where TIPO = 1";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("total");
            }

            conn.close();

        } catch (Exception ex) {
            total = 0;
        }
        return total;
    }
    
    
}

