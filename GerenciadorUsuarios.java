package redesocial.gerenciador;

import redesocial.modelo.Usuario;
import redesocial.util.Validador;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario cadastrarUsuario(String username, String email, String senha) throws Exception {
        Validador.validarUsername(username);
        Validador.validarEmail(email);
        Validador.validarSenha(senha);

        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                throw new Exception("Username já está em uso.");
            }
            if (u.getEmail().equals(email)) {
                throw new Exception("Email já está em uso.");
            }
        }

        Usuario usuario = new Usuario(username, email, senha);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario login(String username, String senha) throws Exception {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        throw new Exception("Usuário ou senha inválidos.");
    }

    public Usuario buscarUsuarioPorUsername(String username) throws Exception {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        throw new Exception("Usuário não encontrado.");
    }

    public void atualizarPerfil(Usuario usuario, String novoUsername, String novoEmail, String novaSenha) throws Exception {
        Validador.validarUsername(novoUsername);
        Validador.validarEmail(novoEmail);
        Validador.validarSenha(novaSenha);

        for (Usuario u : usuarios) {
            if (!u.equals(usuario) && (u.getUsername().equals(novoUsername) || u.getEmail().equals(novoEmail))) {
                throw new Exception("Username ou email já está em uso.");
            }
        }

        usuario.setUsername(novoUsername);
        usuario.setEmail(novoEmail);
        usuario.setSenha(novaSenha);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Usuario> buscarAmigosPorUsername(Usuario usuario, String username) {
        List<Usuario> amigosEncontrados = new ArrayList<>();
        for (Usuario amigo : usuario.getAmigos()) {
            if (amigo.getUsername().contains(username)) {
                amigosEncontrados.add(amigo);
            }
        }
        return amigosEncontrados;
    }
}
