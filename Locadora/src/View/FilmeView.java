package View;

import Classes.Filme;
import DAO.FilmeDAO;

public class FilmeView {

    FilmeDAO filmeDao = new FilmeDAO();

    public void add() {
        Filme filme = new Filme();

        filme.setTitulo(Titulo);

        filme.setTipo(0);

        filme.setQuantidade(0);

        if () {
            
        }
        filmeDao.salvar(filme);
    }

}
