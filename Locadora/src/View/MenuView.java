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

        try {
            option = Integer.parseInt(valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você não botou nenhuma alternativa, tente novamente");
            this.menu();
        }

        switch (option) {
            case 1:
                this.menus("Cliente");
                break;
            case 2:
                this.menus("Filmes");
            case 3:
                this.menuMovimentação();
                break;
            case 4:
                this.relatorios();
                break;
            case 0:
                System.exit(0);
        }
    }

    public void menus(String tela) {
        FilmeView filme = new FilmeView();
        ClienteView cliente = new ClienteView();
        int option = 0;
        String valor = JOptionPane.showInputDialog("JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Cadastro de " + tela + "\n\n"
                + "1 - Inclusão\n"
                + "2 - Alteração\n"
                + "3 - Consulta\n"
                + "4 - Exclusão\n"
                + "0 - Retornar");
        try {
            option = Integer.parseInt(valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você não botou nenhuma alternativa, tente novamente");
            this.menu();
        }

        switch (option) {
            case 1:
                if (tela.equalsIgnoreCase("Cliente")) {
                    cliente.add();
                } else {
                    filme.add();
                }
                break;
            case 2:
                if (tela.equalsIgnoreCase("Cliente")) {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
                        cliente.edit(id);
                    } catch (Exception e) {
                        this.menus("Cliente");
                    }
                } else {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do filme:"));
                        filme.edit(id);
                    } catch (Exception e) {
                    }
                }
                break;
            case 3:
                if (tela.equalsIgnoreCase("Cliente")) {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
                        cliente.view(id);
                        this.menus("Cliente");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Id incorreto ou inexistente");
                        this.menus("Cliente");
                    }
                } else {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do filme:"));
                        filme.view(id);
                        this.menus("Filme");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Id incorreto ou inexistente");
                        this.menus("Filme");
                    }
                }
                break;
            case 4:
                if (tela.equalsIgnoreCase("Cliente")) {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
                        cliente.delete(id);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Id incorreto ou inexistente");
                        this.menus("Cliente");
                    }
                } else {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do filme:"));
                        filme.delete(id);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Id incorreto ou inexistente");
                        this.menus("Filme");
                    }
                }
                break;
            case 0:
                this.menu();
                break;
            default:
                this.menu();
                break;
        }
    }

    public void menuMovimentação() {
        int option = 0;
        String valor = JOptionPane.showInputDialog("JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Movimentação \n\n"
                + "1 - Locação\n"
                + "2 - Devolução\n"
                + "0 - Retornar");
        try {
            option = Integer.parseInt(valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você não botou nenhuma alternativa, tente novamente");
            this.menuMovimentação();
        }
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 0:
                this.menu();
                break;
            default:
                this.menu();
                break;
        }
    }

    public void relatorios() {
        int option = 0;
        String valor = JOptionPane.showInputDialog("JR Comercio de produtos LTDA.\n"
                + "Controle de locadora de vídeo\n"
                + "---------------------------------------------------\n"
                + "Movimentação \n\n"
                + "1 - Balanço do Acervo\n"
                + "2 - Balanço de Movimentações\n"
                + "0 - Retornar");

        try {
            option = Integer.parseInt(valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você não botou nenhuma alternativa, tente novamente");
            this.menuMovimentação();
        }
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 0:
                this.menu();
                break;
            default:
                this.menu();
                break;
        }
    }

}
