package redesocial.modelo;

public class Comentario {
    private Usuario autor;
    private String texto;

    public Comentario(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return autor.getUsername() + ": " + texto;
    }
}
