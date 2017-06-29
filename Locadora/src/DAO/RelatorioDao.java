package DAO;

import Classes.Cliente;
import Classes.Filme;
import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

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
    public int totalEstoque() {                             
        List<Integer> total = new ArrayList();
        int totalEstoque = 0;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select qnt from filme where qnt !=0";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                total.add(rs.getInt("qnt"));
            }
            conn.close();
            
            for(int i = 0; i<total.size();i++){
                totalEstoque = totalEstoque + total.get(i);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhum film em estoque");
        }
        return totalEstoque;
    }
    
    public List<Filme> rankingFilmes() {                             
        List<Filme> total = new ArrayList();
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select count(aluga.codigo_filme) as locacao, aluga.codigo_filme, FILME.TITULO from aluga inner join FILME on aluga.codigo_filme = filme.codigo_filme group by codigo_filme order by locacao desc limit 10";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setCodigo(rs.getInt("codigo_filme"));
                filme.setTitulo(rs.getString("TITULO"));
                filme.setQuantidade(rs.getInt("locacao"));
                total.add(filme);
            }
            conn.close();            
        } catch (Exception ex) {         
            System.out.println(ex);
        }
        return total;
    }
    
    public List<Cliente> rankingCliente() {                             
        List<Cliente> clientes = new ArrayList();        
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_SELECT = "select count(aluga.CODIGO_CLIENTE) as locacao, cliente.CODIGO_CLIENTE, cliente.nome from aluga inner join cliente on aluga.CODIGO_CLIENTE = cliente.codigo_cliente group by CODIGO_CLIENTE order by locacao desc limit 10";

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_SELECT);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente= new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCodigo(rs.getInt("codigo_cliente"));
                cliente.setTelefone(String.valueOf(rs.getInt("locacao")));
                clientes.add(cliente);           
            }            
            conn.close();            
        } catch (Exception ex) {         
            System.out.println(ex);
        }
        return clientes;
    }
    
    
}

