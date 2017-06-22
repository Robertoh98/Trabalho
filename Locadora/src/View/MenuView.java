package View;

import javax.swing.JOptionPane;

public class MenuView {

    public void menu() {
        int option = 0;
        String valor = JOptionPane.showInputDialog("JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Menu principal\n\n"
                + "1 - Cadastro de clientes\n"
                + "2 - Cadastro de filmes\n"
                + "3 - Movimentação\n"
                + "4 - Relatórios\n"
                + "0 - Finalizar");
        if (valor.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Você não botou nenhuma alternativa, tente novamente");
            this.menu();
        } else {
            option = Integer.parseInt(valor);
        }

        switch (option) {
            case 1:
                this.menus("Clientes");
                break;
            case 2:
                this.menus("Filmes");
            case 3:
                this.menuMovimentação();
            case 4:
                this.relatorios();
            case 0:
                System.exit(0);
        }
    }

    public void menus(String tela) {
        FilmeView filme = new FilmeView();
        int option = 0;
        String valor = JOptionPane.showInputDialog("JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Cadastro de " +tela+ "\n\n"
                + "1 - Inclusão\n"
                + "2 - Alteração\n"
                + "3 - Consulta\n"
                + "4 - Exclusão\n"
                + "0 - Retornar");
        
        switch(option){
            case 1:
                if (tela.equalsIgnoreCase("Clientes")) {
                    
                }else{
                    filme.add();
                }
        }
    }
    
    
    
    
}
