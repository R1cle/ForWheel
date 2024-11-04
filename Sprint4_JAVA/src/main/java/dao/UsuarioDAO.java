package dao;

import to.UsuarioTO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Repository implements RepositorioGenerico<UsuarioTO> {
    @Override
    public void adicionar(UsuarioTO usuario) {
        String query = "INSERT INTO USUARIO_FW (ID_USUARIO, NOME_COMPLETO, " +
                "EMAIL, SENHA, CPF, " +
                "DATA_NASCIMENTO, TELEFONE, CIDADE, BAIRRO, LOGRADOURO, " +
                "UF, COMPLEMENTO) " +
                "VALUES " +
                "(SEQ_CARRO_FW.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setDate(5,  usuario.getDataNascimento());
            stmt.setString(6, usuario.getTelefone());
            stmt.setString(7, usuario.getCidade());
            stmt.setString(8, usuario.getBairro());
            stmt.setString(9, usuario.getLogradouro());
            stmt.setString(10, usuario.getUf());
            stmt.setString(11, usuario.getComplemento());

            // Executa a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Erro ao atualizar o carro!");
        } finally {
            closeConnection();
        }
    }


    @Override
    public List<UsuarioTO> listar() {
        List<UsuarioTO> usuarios = new ArrayList<>();
        String query = "SELECT * FROM USUARIO_FW";
        try (PreparedStatement stmt = getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_USUARIO");
                String nomeCompleto = rs.getString("NOME_COMPLETO");
                String email = rs.getString("EMAIL");
                String senha = rs.getString("SENHA");
                String cpf = rs.getString("CPF");
                Date dataNascimento = rs.getDate("DATA_NASCIMENTO");
                String telefone = rs.getString("TELEFONE");
                String cidade = rs.getString("CIDADE");
                String bairro = rs.getString("BAIRRO");
                String logradouro = rs.getString("LOGRADOURO");
                String uf = rs.getString("UF");
                String complemento = rs.getString("COMPLEMENTO");
                UsuarioTO usuario = new UsuarioTO(id, nomeCompleto, email, senha, cpf, dataNascimento, telefone, cidade, bairro, logradouro, uf, complemento);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.print("Erro ao atualizar o carro!");
        }finally {
            closeConnection();
        }
        return usuarios;
    }

    @Override
    public void removerPorId(int id) {
        String query = "DELETE FROM USUARIO_FW WHERE ID_USUARIO = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Erro ao atualizar o carro!");
        }finally {
            closeConnection();
        }
    }

    @Override
    public void atualizarPorId(int id, UsuarioTO usuarioAtualizado) {
        String query = "UPDATE USUARIO_FW SET NOME_COMPLETO = ?, EMAIL = ?, SENHA = ?, CPF = ?, DATA_NASCIMENTO = ?, TELEFONE = ?, CIDADE = ?, BAIRRO = ?, LOGRADOURO = ?, UF = ?, COMPLEMENTO = ? WHERE ID_USUARIO = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, usuarioAtualizado.getNomeCompleto());
            stmt.setString(2, usuarioAtualizado.getEmail());
            stmt.setString(3, usuarioAtualizado.getSenha());
            stmt.setString(4, usuarioAtualizado.getCpf());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String data = sdf.format(usuarioAtualizado.getDataNascimento());
            stmt.setString(5, data);
            stmt.setString(6, usuarioAtualizado.getTelefone());
            stmt.setString(7, usuarioAtualizado.getCidade());
            stmt.setString(8, usuarioAtualizado.getBairro());
            stmt.setString(9, usuarioAtualizado.getLogradouro());
            stmt.setString(10, usuarioAtualizado.getUf());
            stmt.setString(11, usuarioAtualizado.getComplemento());
            stmt.setInt(12, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Erro ao atualizar o carro!");
        }finally {
            closeConnection();
        }
    }
}
