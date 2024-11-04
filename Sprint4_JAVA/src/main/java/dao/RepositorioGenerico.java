package dao;

import to._EntidadeBaseTO;
import java.util.List;

public interface RepositorioGenerico<T extends _EntidadeBaseTO> {
    void adicionar(T entidade);
    List<T> listar();
    void removerPorId(int id);
    void atualizarPorId(int id, T entidade);
}
