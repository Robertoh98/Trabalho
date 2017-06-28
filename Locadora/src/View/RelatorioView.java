package View;

import DAO.FilmeDAO;
import Classes.Filme;
import DAO.RelatorioDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RelatorioView {

    MenuView menu = new MenuView();

    public void balancoAcervo() {
        FilmeDAO filmeDao = new FilmeDAO();
        List<Filme> lista = new ArrayList<Filme>();
        lista = filmeDao.listar();
        String mensagem = "COD TÍTULO TIPO QUANTIDADE\n\n";
        String tipo;
        for (Filme f : lista) {
            if (f.getTipo() == 0) {
                tipo = "24h";
            } else {
                tipo = "48h";
            }
            mensagem = mensagem + " " + f.getCodigo() + " " + f.getTitulo() + " " + tipo + " " + f.getQuantidade() + "\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);

        menu.menuMovimentação();
    }

    public void balancoMovimentacoes() {
        RelatorioDao relatorioDao = new RelatorioDao();
        int totalCliente = relatorioDao.numeroClientes();
        int totalFilmes = relatorioDao.numeroFilmes();
        int totalLocacaoMensal = relatorioDao.locacoesMensal();
        int total24 = relatorioDao.filmes24();
        int total48 = relatorioDao.filmes48();
        
        System.out.println(totalCliente + " " + totalFilmes + " " + totalLocacaoMensal + " " + total24 + " " + total48);
    }

}
