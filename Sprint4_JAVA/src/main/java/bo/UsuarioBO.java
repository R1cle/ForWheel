package bo;
import dao.UsuarioDAO;
import to.UsuarioTO;

import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public void adicionarUsuario(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        // Validacoes aqui ( eu deletei aquelas da package utils mas da pra usar aq )
        usuarioDAO.adicionar(usuario);
    }

    public List<UsuarioTO> listarUsuarios() {
        usuarioDAO = new UsuarioDAO();
        // Validacoes aqui
        return usuarioDAO.listar();
    }

    public void removerUsuario(int id) {
        usuarioDAO = new UsuarioDAO();
        // Validacoes aqui
        usuarioDAO.removerPorId(id);
    }

    public void atualizarUsuario(int id, UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        // Validacoes aqui
        usuarioDAO.atualizarPorId(id, usuario);
    }
}
