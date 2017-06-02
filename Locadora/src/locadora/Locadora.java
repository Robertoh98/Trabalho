package locadora;

import Classes.Cliente;
import DAO.ClienteDAO;
import java.util.List;

/**
 *
 * @author Roberto & Joao
 */
public class Locadora {
    public static void main(String[] args) {        
        ClienteDAO clienteDao = new ClienteDAO();               
        
       Cliente cliente = new Cliente();
       
       cliente.setNome("Roberto");
       cliente.setTelefone("123");   
       cliente.setCodigo(2);
       
       clienteDao.editar(cliente);
        
    }
    
}
