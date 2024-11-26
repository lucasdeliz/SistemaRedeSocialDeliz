package redesocial.ui;

import redesocial.gerenciador.GerenciadorUsuarios;
import redesocial.modelo.Usuario;

import java.util.Scanner;

public class MenuUsuarios {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;

    public MenuUsuarios(GerenciadorUsuarios gerenciadorUsuarios, Scanner scanner) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = scanner;
    }

    public Usuario menuCadastroLogin() {
        while (true) {
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        return gerenciadorUsuarios.cadastrarUsuario(username, email, senha);
                    case 2:
                        System.out.print("Username: ");
                        username = scanner.nextLine();
                        System.out.print("Senha: ");
                        senha = scanner.nextLine();
                        return gerenciadorUsuarios.login(username, senha);
                    case 0:
                        return null;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
