package redesocial.ui;

import redesocial.gerenciador.*;
import redesocial.modelo.*;
import redesocial.exception.*;

import java.util.*;

public class MenuPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
    private static GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();
    private static GerenciadorAmizades gerenciadorAmizades = new GerenciadorAmizades();
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            if (usuarioLogado == null) {
                exibirMenuInicial();
            } else {
                exibirMenuPrincipal();
            }
        }
    }

    private static void exibirMenuInicial() {
        System.out.println("\n=== Menu Inicial ===");
        System.out.println("1. Cadastro de Usuário");
        System.out.println("2. Login");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir o \n

        switch (opcao) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                login();
                break;
            case 0:
                System.out.println("Saindo...");
                System.exit(0);  // Encerra o programa
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Ver Feed de Amigos");
        System.out.println("2. Criar Post");
        System.out.println("3. Listar Posts");
        System.out.println("4. Amizades");
        System.out.println("5. Listar Amigos");  // Adicionada a opção
        System.out.println("6. Logout");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o \n

        switch (opcao) {
            case 1:
                verFeedAmigos();
                break;
            case 2:
                criarPost();
                break;
            case 3:
                listarPosts();
                break;
            case 4:
                menuAmizades();
                break;
            case 5:
                listarAmigos();  // Chama a função listar amigos
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }

    private static void cadastrarUsuario() {
        try {
            System.out.print("Digite o nome de usuário: ");
            String username = scanner.nextLine();
            System.out.print("Digite o email: ");
            String email = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            gerenciadorUsuarios.cadastrarUsuario(username, email, senha);
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private static void login() {
        try {
            System.out.print("Digite o nome de usuário: ");
            String username = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            usuarioLogado = gerenciadorUsuarios.login(username, senha);
            System.out.println("Login realizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private static void logout() {
        System.out.println("Você foi desconectado.");
        usuarioLogado = null; // Limpa a variável para "deslogar"
    }

    private static void verFeedAmigos() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para ver o feed.");
            return;
        }

        try {
            List<Post> posts = gerenciadorPosts.listarPostsDeAmigos(usuarioLogado);
            for (Post post : posts) {
                System.out.println("- " + post.getConteudo() + " (Autor: " + post.getAutor().getUsername() + ")");
                System.out.println("1. Curtir | 2. Comentar | 0. Voltar");
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir o \n

                switch (opcao) {
                    case 1:
                        gerenciadorPosts.curtirPost(usuarioLogado, post);
                        System.out.println("Post curtido!");
                        break;
                    case 2:
                        System.out.print("Digite o comentário: ");
                        String comentario = scanner.nextLine();
                        gerenciadorPosts.comentarPost(usuarioLogado, post, comentario);
                        System.out.println("Comentário adicionado!");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao exibir o feed de amigos: " + e.getMessage());
        }
    }

    private static void criarPost() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para criar um post.");
            return;
        }

        try {
            System.out.print("Digite o conteúdo do post: ");
            String conteudo = scanner.nextLine();
            gerenciadorPosts.criarPost(usuarioLogado, conteudo);
            System.out.println("Post criado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar post: " + e.getMessage());
        }
    }

    private static void listarPosts() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para listar os posts.");
            return;
        }

        try {
            List<Post> posts = gerenciadorPosts.listarPostsPorUsuario(usuarioLogado);
            for (Post post : posts) {
                System.out.println(post.getConteudo());
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar posts: " + e.getMessage());
        }
    }

    private static void listarAmigos() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para listar seus amigos.");
            return;
        }

        try {
            List<Usuario> amigos = gerenciadorAmizades.listarAmigos(usuarioLogado);
            if (amigos.isEmpty()) {
                System.out.println("Você ainda não tem amigos.");
            } else {
                System.out.println("Lista de Amigos:");
                for (Usuario amigo : amigos) {
                    System.out.println("- " + amigo.getUsername());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar amigos: " + e.getMessage());
        }
    }

    private static void menuAmizades() {
        System.out.println("\n=== Menu de Amizades ===");
        System.out.println("1. Enviar Solicitação de Amizade");
        System.out.println("2. Aceitar Solicitação de Amizade");
        System.out.println("3. Remover Amigo");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o \n

        switch (opcao) {
            case 1:
                enviarSolicitacaoAmizade();
                break;
            case 2:
                aceitarSolicitacaoAmizade();
                break;
            case 3:
                removerAmigo();
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }

    private static void enviarSolicitacaoAmizade() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para enviar uma solicitação.");
            return;
        }

        try {
            System.out.print("Digite o nome de usuário do amigo: ");
            String usernameAmigo = scanner.nextLine();
            Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);

            if (amigo == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            boolean solicitacaoEnviada = gerenciadorAmizades.enviarSolicitacao(usuarioLogado, amigo);
            if (solicitacaoEnviada) {
                System.out.println("Solicitação de amizade enviada com sucesso.");
            } else {
                System.out.println("Você já enviou uma solicitação para este usuário.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao enviar solicitação de amizade: " + e.getMessage());
        }
    }

    private static void aceitarSolicitacaoAmizade() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para aceitar solicitações.");
            return;
        }

        try {
            System.out.print("Digite o nome de usuário da pessoa que enviou a solicitação: ");
            String usernameAmigo = scanner.nextLine();
            Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);

            if (amigo == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            boolean solicitacaoAceita = gerenciadorAmizades.aceitarSolicitacao(usuarioLogado, amigo);
            if (solicitacaoAceita) {
                System.out.println("Solicitação de amizade aceita!");
            } else {
                System.out.println("Não há solicitação pendente.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao aceitar solicitação de amizade: " + e.getMessage());
        }
    }

    private static void removerAmigo() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para remover um amigo.");
            return;
        }

        try {
            System.out.print("Digite o nome de usuário do amigo que deseja remover: ");
            String usernameAmigo = scanner.nextLine();

            Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);
            if (amigo == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            boolean amizadeRemovida = gerenciadorAmizades.removerAmizade(usuarioLogado, amigo);
            if (amizadeRemovida) {
                System.out.println("Amizade removida com sucesso.");
            } else {
                System.out.println("Erro ao remover amizade.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao remover amigo: " + e.getMessage());
        }
    }
}
