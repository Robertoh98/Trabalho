package DAO;

import Classes.Cliente;
import conexao.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClienteDAO {

    public int salvar(Cliente cliente) {
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;
        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into CLIENTE (NOME, TELEFONE) values ( ?, ?)";
            String QUERY_UPDATE = "update CLIENTE set NOME = ?, TELEFONE = ? where CODIGO_CLIENTE = ?";

            if (cliente.getCodigo() == null){

                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getTelefone());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }

            } else {

                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getTelefone());
                stmt.setInt(3, cliente.getCodigo());

                stmt.executeUpdate();
                resultado = cliente.getCodigo();
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;

        }

        return resultado;
    }

    public boolean excluir(Usuario usuario) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from USUARIO where idUsuario = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;

    }

}
