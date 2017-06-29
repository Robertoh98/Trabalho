package View;

import Classes.Cliente;
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
        int totalEstoque = relatorioDao.totalEstoque();
        List<Filme> rankingFilmes = relatorioDao.rankingFilmes();
        List<Cliente> rankingClientes = relatorioDao.rankingCliente();
        String rankFilmes = "";
        String rankCliente = "";
        int i = 1;
        for (Filme f : rankingFilmes) {
            rankFilmes = rankFilmes +"\n"+i+"- "+f.getCodigo()+" "+ f.getTitulo()+" "+f.getQuantidade();
            i++;
        }
        int j = 1;
        for (Cliente c : rankingClientes) {
            rankCliente = rankCliente +"\n"+j+"- "+c.getCodigo()+" "+ c.getNome()+" "+c.getTelefone();            
            j++;
        }
        JOptionPane.showMessageDialog(null, "JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Balanço acervo\n"
                + "Total clientes: " + totalCliente
                + "\nTotal filmes acervos: " + totalFilmes
                + "\nTotal locações no mês: " + totalLocacaoMensal
                + "\nFilmes 24 horas" + total24
                + "\nFilmes 48 horas" + total48
                + "\nFilmes: " + totalEstoque
                +"\n\nTop 10 Filmes:\n"+ rankFilmes
                +"\n\nTop 10 Clientes:\n" + rankCliente);       
    }

}
