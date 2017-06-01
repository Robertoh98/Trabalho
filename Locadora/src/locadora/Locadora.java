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
        
        List<Cliente> clientes = clienteDao.listar();
        
        for(Cliente c : clientes){
            System.out.println(c.getCodigo());
            System.out.println(c.getNome());
            System.out.println(c.getTelefone());
        }
        
    }
    
}
