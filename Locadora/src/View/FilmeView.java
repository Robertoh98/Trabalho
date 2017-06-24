package View;

import Classes.Filme;
import DAO.FilmeDAO;
import javax.swing.JOptionPane;

public class FilmeView {

    FilmeDAO filmeDao = new FilmeDAO();
    MenuView menu = new MenuView();

    public void add() {
        Filme filme = new Filme();

        String titulo = "";
        Integer tipo = null;
        Integer qnt = null;

        titulo = JOptionPane.showInputDialog("Qual o titulo do filme:");
        tipo = Integer.parseInt(JOptionPane.showInputDialog("Qual o tipo do filme:\n\n"
                + "0 - Devolução em 24 horas.\n"
                + "1 - Devolução em 48 horas."));
        qnt = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade:"));

        if (titulo == "" || tipo == null || qnt == null) {
            System.out.println("Os valores estão em branco");
            this.add();
        } else {
            if (titulo == "") {
                System.out.println("O titulo está em branco");
            }
            if (tipo == null) {
                System.out.println("O tipo está em branco");
            } else if (qnt == null) {
                System.out.println("A quantidade está em branco");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar?") == 0) {
                    filme.setTitulo(titulo);
                    filme.setTipo(tipo);
                    filme.setQuantidade(qnt);

                    filmeDao.salvar(filme);
                    JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");
                    menu.menus("Filme");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível adicionar");
                    menu.menus("Filme");
                }

            }
        }
    }

    public void delete(int id) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?") == 0) {
            try {
                Filme filme = filmeDao.findOne(id);
                filmeDao.excluir(filme);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                menu.menus("Filme");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir");
            menu.menus("Filme");
        }
    }

    public void edit(int id) {
        try {

            Filme filme = this.view(id);

            int edicao = Integer.parseInt(JOptionPane.showInputDialog("Qual dado deseja alterar?\n"
                    + "1 - Titulo\n"
                    + "2 - Quantidade\n"
                    + "3 - Tipo"));

            switch (edicao) {
                case 1:
                    filme.setTitulo(JOptionPane.showInputDialog("Digite um novo título para o filme:\n" + filme.getTitulo()));
                    break;
                case 2:
                    filme.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo numero total de quantidade")));
                    break;
                case 3:
                    filme.setTipo(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo tipo do filme:\n\n"
                            + "0 - Devolução em 24 horas.\n" + "1 - Devolução em 48 horas.")));
                    break;
                case 0:
                    menu.menus("Filme");
                    break;
                default:
                    menu.menus("Filme");
                    break;
            }

            filmeDao.editar(filme);
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            menu.menus("Filme");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Não foi possível editar");
            menu.menus("Filme");
        }
    }

    public Filme view(int id) {
        Filme filme = new Filme();
        try {
            filme = filmeDao.findOne(id);
            if (filme.getCodigo() == null) {
                JOptionPane.showMessageDialog(null, "Id inexistente!");
                menu.menus("Filme");
            }
            String tipo = "";
            if (filme.getTipo() == 0) {
                tipo = "Devolução 24 horas.";
            } else {
                tipo = "Devolução 48 horas.";
            }

            JOptionPane.showMessageDialog(null, "Filme: " + filme.getTitulo()
                    + "\n Quantitade em estoque: " + filme.getQuantidade()
                    + "\n Tipo: " + tipo);

        } catch (Exception e) {
            menu.menus("Filme");
        }
        return filme;
    }

}
