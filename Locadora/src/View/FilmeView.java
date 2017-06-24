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

        titulo = JOptionPane.showInputDialog("Qual o titulo do filme");
        tipo = Integer.parseInt(JOptionPane.showInputDialog("Qual o titulo do filme"));
        qnt = Integer.parseInt(JOptionPane.showInputDialog("Qual o titulo do filme"));

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

}
