package View;

import javax.swing.JOptionPane;
import Classes.Cliente;
import Classes.Filme;
import Classes.Aluga;
import DAO.ClienteDAO;
import DAO.FilmeDAO;
import DAO.AlugaDAO;

public class AlugaView {

    MenuView menu = new MenuView();
    
    public void locar() {

        Cliente cliente = new Cliente();
        ClienteDAO clienteDao = new ClienteDAO();
        FilmeDAO filmeDao = new FilmeDAO();
        Filme filme = new Filme();
        AlugaDAO alugaDao = new AlugaDAO();
        Aluga aluga = new Aluga();

        int option = Integer.parseInt(JOptionPane.showInputDialog("Escreva o código do cliente que deseja alugar"));
        cliente = clienteDao.findOne(option);

        option = Integer.parseInt(JOptionPane.showInputDialog("Escreva o código do filme que deseja alugar"));
        filme = filmeDao.findOne(option);

        if (filme.getQuantidade() <= 0) {
            JOptionPane.showMessageDialog(null, "Não há este título em estoque");
        } else {
            aluga.setCliente(cliente);
            aluga.setFilme(filme);
            alugaDao.alugar(aluga);
        }
        JOptionPane.showMessageDialog(null, "Alugado com sucesso");
        menu.menuMovimentação();
    }

    public void devolver() {

        Cliente cliente = new Cliente();
        ClienteDAO clienteDao = new ClienteDAO();
        FilmeDAO filmeDao = new FilmeDAO();
        Filme filme = new Filme();
        AlugaDAO alugaDao = new AlugaDAO();
        Aluga aluga = new Aluga();

        int option = Integer.parseInt(JOptionPane.showInputDialog("Escreva o código do cliente que deseja devolver"));
        cliente = clienteDao.findOne(option);

        option = Integer.parseInt(JOptionPane.showInputDialog("Escreva o código do filme que deseja devolver"));
        filme = filmeDao.findOne(option);

        aluga = alugaDao.findOne(cliente.getCodigo(), filme.getCodigo());
        
        alugaDao.devolução(aluga);
        
        JOptionPane.showMessageDialog(null, "Devolvido com sucesso");
        menu.menuMovimentação();
    }
}
