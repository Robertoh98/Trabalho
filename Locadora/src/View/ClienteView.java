package View;

import Classes.Cliente;
import DAO.ClienteDAO;
import javax.swing.JOptionPane;

public class ClienteView {

    ClienteDAO clienteDao = new ClienteDAO();
    MenuView menu = new MenuView();

    public void add() {
        Cliente cliente = new Cliente();

        String nome = "";
        String telefone = "";

        nome = JOptionPane.showInputDialog("Qual o nome do cliente:");
        telefone = JOptionPane.showInputDialog("Qual o telefone do cliente:");

        if (nome == "" || telefone == "") {
            System.out.println("Os valores estão em branco");
            this.add();
        } else {
            if (nome == "") {
                System.out.println("O nome está em branco");
                nome = JOptionPane.showInputDialog("Qual o nome do cliente:");
            } else if (telefone == "") {
                System.out.println("O telefone está em branco");
                telefone = JOptionPane.showInputDialog("Qual o telefone do cliente:");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar?") == 0) {
                    cliente.setNome(nome);
                    cliente.setTelefone(telefone);

                    clienteDao.salvar(cliente);
                    JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");
                    if (JOptionPane.showConfirmDialog(null, "Adicionar novo?") == 0) {
                        this.add();
                    } else {
                        menu.menus("Cliente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível adicionar");
                    menu.menus("Cliente");
                }

            }
        }
    }

    public void delete(int id) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?") == 0) {
            try {
                Cliente cliente = clienteDao.findOne(id);
                clienteDao.excluir(cliente);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                menu.menus("Cliente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir");
                this.delete(id);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir");
            menu.menus("Cliente");
        }
    }

    public void edit(int id) {
        try {

            Cliente cliente = this.view(id);

            int edicao = Integer.parseInt(JOptionPane.showInputDialog("Qual dado deseja alterar?\n"
                    + "1 - Nome\n"
                    + "2 - Telefone"));

            switch (edicao) {
                case 1:
                    cliente.setNome(JOptionPane.showInputDialog("Digite um novo nome para o cliente:\n" + cliente.getNome()));
                    break;
                case 2:
                    cliente.setTelefone(JOptionPane.showInputDialog("Digite o novo numero de telefone:\n Antigo: " + cliente.getTelefone()));
                    break;
                case 0:
                    menu.menus("Cliente");
                    break;
                default:
                    menu.menus("Cliente");
                    break;
            }
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar?") == 0) {

                clienteDao.editar(cliente);
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                menu.menus("Cliente");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível editar");
                this.edit(id);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Não foi possível editar");
            this.edit(id);
        }
    }

    public Cliente view(int id) {
        Cliente cliente = new Cliente();
        try {
            cliente = clienteDao.findOne(id);
            if (cliente.getCodigo() == null) {
                JOptionPane.showMessageDialog(null, "Id inexistente!");
                menu.menus("Cliente");
            }

            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getNome()
                    + "\n Telefone: " + cliente.getTelefone());

        } catch (Exception e) {
            menu.menus("Cliente");
        }
        return cliente;
    }

}
