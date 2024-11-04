package dao;

import to.CarroTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends Repository implements RepositorioGenerico<CarroTO> {

    @Override
    public void adicionar(CarroTO entidade) {
        try {
            String query = "INSERT INTO CARRO_FW (ID_CARRO, MARCA, " +
                    "MODELO, ANO, PLACA, " +
                    "USUARIO_ID, DATA_REGISTRO) VALUES " +
                    "(SEQ_CARRO_FW.NEXTVAL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, entidade.getMarca());
            stmt.setString(2, entidade.getModelo());
            stmt.setInt(3, entidade.getAno());
            stmt.setString(4, entidade.getPlaca());
            stmt.setInt(5, entidade.getUsuarioId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<CarroTO> listar() {
        List<CarroTO> carros = new ArrayList<>();
        try {
            String query = "SELECT * FROM CARRO_FW";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCarro = rs.getInt("ID_CARRO");
                String marca = rs.getString("MARCA");
                String modelo = rs.getString("MODELO");
                int ano = rs.getInt("ANO");
                String placa = rs.getString("PLACA");
                int usuarioId = rs.getInt("USUARIO_ID");
                carros.add(new CarroTO(idCarro, marca, modelo, ano, placa, usuarioId));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return carros;
    }

    @Override
    public void removerPorId(int id) {
        try {
            String query = "DELETE FROM CARRO_FW WHERE ID_CARRO = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    @Override
    public void atualizarPorId(int id, CarroTO novoCarro) {
        try {
            // Atualiza com base no modelo
            String query = "UPDATE CARRO_FW SET MARCA = ?, MODELO = ?, ANO = ?, PLACA = ?, USUARIO_ID = ? WHERE PLACA = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, novoCarro.getMarca());
            stmt.setString(2, novoCarro.getModelo());
            stmt.setInt(3, novoCarro.getAno());
            stmt.setString(4, novoCarro.getPlaca());
            stmt.setInt(5, novoCarro.getUsuarioId());
            stmt.setInt(6, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public void atualizarPorPlaca(String placa, CarroTO novoCarro) {
        try {
            // Atualiza com base no modelo
            String query = "UPDATE CARRO_FW SET MARCA = ?, MODELO = ?, ANO = ?, PLACA = ?, USUARIO_ID = ? WHERE PLACA = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, novoCarro.getMarca());
            stmt.setString(2, novoCarro.getModelo());
            stmt.setInt(3, novoCarro.getAno());
            stmt.setString(4, novoCarro.getPlaca());
            stmt.setInt(5, novoCarro.getUsuarioId());
            stmt.setString(6, placa);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }
}
