package redesocial.ui;

import redesocial.gerenciador.GerenciadorPosts;
import redesocial.modelo.Usuario;

import java.util.Scanner;

public class MenuPosts {
    private GerenciadorPosts gerenciadorPosts;
    private Scanner scanner;

    public MenuPosts(GerenciadorPosts gerenciadorPosts, Scanner scanner) {
        this.gerenciadorPosts = gerenciadorPosts;
        this.scanner = scanner;
    }

    public void exibirMenuPosts(Usuario usuarioLogado) {
        while (true) {
            System.out.println("1. Criar Post");
            System.out.println("2. Listar Meus Posts");
            System.out.println("0. Voltar");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Conteúdo do post: ");
                        String conteudo = scanner.nextLine();
                        gerenciadorPosts.criarPost(usuarioLogado, conteudo);
                        System.out.println("Post criado com sucesso!");
                        break;
                    case 2:
                        gerenciadorPosts.listarPostsPorUsuario(usuarioLogado);
                        break;
                    case 0:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
