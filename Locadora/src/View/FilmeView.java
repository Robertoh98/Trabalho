package View;

import Classes.Filme;
import DAO.FilmeDAO;

public class FilmeView {

    FilmeDAO filmeDao = new FilmeDAO();

    public void add() {
        Filme filme = new Filme();
                

        String titulo = "";
        Integer tipo = null;
        Integer qnt = null;

        if (titulo == "" || tipo == null || qnt == null) {
            System.out.println("Os valores estão em branco");
        } else {
            if (titulo == "") {
                System.out.println("O titulo está em branco");
            }
            if (tipo == null) {
                System.out.println("O tipo está em branco");
            } else if (qnt == null) {
                System.out.println("A quantidade está em branco");
            } else {
                filme.setTitulo(titulo);
                filme.setTipo(tipo);
                filme.setQuantidade(qnt);

                filmeDao.salvar(filme);
            }
        }
    }

}
