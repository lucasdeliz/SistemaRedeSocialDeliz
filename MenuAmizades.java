package redesocial.ui;

import redesocial.gerenciador.GerenciadorAmizades;
import redesocial.modelo.Usuario;

import java.util.Scanner;

public class MenuAmizades {
    private GerenciadorAmizades gerenciadorAmizades;
    private Scanner scanner;

    public MenuAmizades(GerenciadorAmizades gerenciadorAmizades, Scanner scanner) {
        this.gerenciadorAmizades = gerenciadorAmizades;
        this.scanner = scanner;
    }

    public void exibirMenuAmizades(Usuario usuarioLogado) {
        while (true) {
            System.out.println("1. Enviar Solicitação de Amizade");
            System.out.println("2. Aceitar Solicitação de Amizade");
            System.out.println("3. Listar Amigos");
            System.out.println("0. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o username do destinatário: ");
                        String destinatarioUsername = scanner.nextLine();
                        gerenciadorAmizades.enviarSolicitacao(usuarioLogado,
                                usuarioLogado.buscarAmigoPorUsername(destinatarioUsername));
                        System.out.println("Solicitação enviada com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o username do remetente: ");
                        String remetenteUsername = scanner.nextLine();
                        gerenciadorAmizades.aceitarSolicitacao(usuarioLogado,
                                usuarioLogado.buscarAmigoPorUsername(remetenteUsername));
                        System.out.println("Solicitação aceita!");
                        break;
                    case 3:
                        gerenciadorAmizades.listarAmigos(usuarioLogado);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
