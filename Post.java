package redesocial.modelo;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private Usuario autor;
    private String conteudo;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    // Método para curtir o post
    public void curtir(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
        } else {
            throw new Exception("Usuário já curtiu esse post.");
        }
    }

    // Método para comentar no post
    public void comentar(Usuario usuario, String texto) throws Exception {
        if (usuario == null || texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("Usuário ou texto inválido.");
        }
        Comentario comentario = new Comentario(usuario, texto);
        comentarios.add(comentario);
    }

    // Método para remover um comentário
    public void removerComentario(Comentario comentario) throws Exception {
        if (comentario == null) {
            throw new IllegalArgumentException("Comentário inválido.");
        }
        if (!comentarios.contains(comentario)) {
            throw new Exception("Comentário não encontrado.");
        }
        comentarios.remove(comentario);
    }
}
