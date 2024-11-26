package redesocial.gerenciador;

import redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorAmizades {

    public boolean enviarSolicitacao(Usuario remetente, Usuario destinatario) throws Exception {
        if (destinatario == null) {
            throw new Exception("Usuário destinatário não encontrado.");
        }
        if (destinatario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação já enviada.");
        }
        destinatario.getSolicitacoesAmizade().add(remetente);
        return true;
    }

    public boolean aceitarSolicitacao(Usuario usuario, Usuario remetente) throws Exception {
        if (!usuario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação não encontrada.");
        }
        usuario.getSolicitacoesAmizade().remove(remetente);
        usuario.getAmigos().add(remetente);
        remetente.getAmigos().add(usuario);
        return true;
    }

    public void recusarSolicitacao(Usuario usuario, Usuario remetente) throws Exception {
        if (!usuario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação não encontrada.");
        }
        usuario.getSolicitacoesAmizade().remove(remetente);
    }

    public boolean removerAmizade(Usuario usuario, Usuario amigo) throws Exception {
        if (!usuario.getAmigos().contains(amigo)) {
            throw new Exception("Amizade não encontrada.");
        }
        usuario.getAmigos().remove(amigo);
        amigo.getAmigos().remove(usuario);
        return true;
    }

    public List<Usuario> listarAmigos(Usuario usuario) {
        return new ArrayList<>(usuario.getAmigos());
    }
}
