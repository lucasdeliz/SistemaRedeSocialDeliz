package redesocial.modelo;

import java.util.*;

public class Usuario {
    private String username;
    private String email;
    private String senha;
    private List<Usuario> amigos;
    private List<Usuario> solicitacoesAmizade;
    private List<Post> posts;

    public Usuario(String username, String email, String senha) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.amigos = new ArrayList<>();
        this.solicitacoesAmizade = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void adicionarAmigo(Usuario amigo) {
        amigos.add(amigo);
    }

    public void removerAmigo(Usuario amigo) {
        amigos.remove(amigo);
    }

    public List<Usuario> getSolicitacoesAmizade() {
        return solicitacoesAmizade;
    }

    public void enviarSolicitacaoAmizade(Usuario amigo) {
        if (!solicitacoesAmizade.contains(amigo) && !amigos.contains(amigo)) {
            solicitacoesAmizade.add(amigo);
        }
    }

    public void aceitarSolicitacao(Usuario amigo) {
        if (solicitacoesAmizade.contains(amigo)) {
            amigos.add(amigo);
            solicitacoesAmizade.remove(amigo);
        }
    }

    public void recusarSolicitacao(Usuario amigo) {
        solicitacoesAmizade.remove(amigo);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void adicionarPost(Post post) {
        posts.add(post);
    }

    public void removerPost(Post post) {
        posts.remove(post);
    }

    public void atualizarPerfil(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean isAmigo(Usuario usuario) {
        return amigos.contains(usuario);
    }

    // Método para buscar amigo por username na lista de amigos
    public Usuario buscarAmigoPorUsername(String username) throws Exception {
        for (Usuario amigo : amigos) {
            if (amigo.getUsername().equals(username)) {
                return amigo;
            }
        }
        throw new Exception("Amigo com o username " + username + " não encontrado.");
    }
}
