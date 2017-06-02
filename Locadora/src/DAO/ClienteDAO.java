package DAO;

import Classes.Cliente;
import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public int salvar(Cliente cliente) {       
        int resultado = -1;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
            
            String QUERY_SELECT = "SELECT * FROM CLIENTE WHERE NOME = ?";
            
            ResultSet rs = null;
            
            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setString(1, cliente.getNome());
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                System.out.println("já existe!");
            }else{

            String QUERY_INSERT = "insert into CLIENTE (NOME, TELEFONE) values ( ?, ?)";            
            stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());

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
    
    public Cliente findOne(int id) {

        Cliente cliente = new Cliente();
        
        try {

            String QUERY_DETALHE = "select * from CLIENTE where CODIGO_CLIENTE = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("CODIGO_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setTelefone(rs.getString("TELEFONE"));                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            cliente = null;
            
        }
        
        return cliente;
    }
    
    public int editar(Cliente cliente) {       
        int resultado = -1;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
            
            String QUERY_SELECT = "SELECT * FROM CLIENTE WHERE NOME = ?";
            
            ResultSet rs = null;
            
            stmt = conn.prepareStatement(QUERY_SELECT);
            stmt.setString(1, cliente.getNome());
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                System.out.println("já existe!");
            }else{

            String QUERY_UPDATE = "update CLIENTE set NOME = ?, TELEFONE = ? where CODIGO_CLIENTE = ?";           
            stmt = conn.prepareStatement(QUERY_UPDATE);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getCodigo());

            stmt.executeUpdate();
            
            conn.close();
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;

        }

        return resultado;
    }
    
    

    public boolean excluir(Cliente cliente) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "DELETE FROM CLIENTE WHERE CODIGO_CLIENTE = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, cliente.getCodigo());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;

    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            String QUERY_DETALHE = "SELECT * FROM CLIENTE";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("CODIGO_CLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setTelefone(rs.getString("TELEFONE"));
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
