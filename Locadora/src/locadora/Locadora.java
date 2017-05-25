package locadora;

import conexao.ConnectionManager;
import java.sql.Connection;

/**
 *
 * @author Roberto & Joao
 */
public class Locadora {
    public static void main(String[] args) {
        
        Connection con = ConnectionManager.getConnection();
        
    }
    
}
