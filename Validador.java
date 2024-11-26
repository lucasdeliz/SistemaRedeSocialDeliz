package redesocial.util;

import java.util.regex.Pattern;

public class Validador {

    public static void validarUsername(String username) throws IllegalArgumentException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("O username não pode ser vazio.");
        }
    }

    public static void validarEmail(String email) throws IllegalArgumentException {
        if (email == null || email.trim().isEmpty() ||
                !Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", email)) {
            throw new IllegalArgumentException("O email é inválido.");
        }
    }

    public static void validarSenha(String senha) throws IllegalArgumentException {
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
    }

    public static void validarPost(String conteudo) throws IllegalArgumentException {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do post não pode ser vazio.");
        }
    }

    public static void validarComentario(String comentario) throws IllegalArgumentException {
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new IllegalArgumentException("O comentário não pode ser vazio.");
        }
    }
}
