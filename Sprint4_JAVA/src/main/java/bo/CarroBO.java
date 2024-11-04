package bo;

import dao.CarroDAO;
import to.CarroTO;

import java.util.List;

public class CarroBO {

    CarroDAO carroDAO = new CarroDAO();

    public void adicionarCarro(CarroTO carro) throws Exception {

        carroDAO.adicionar(carro);
    }

    public List<CarroTO> listarCarros() {
        try {
            return carroDAO.listar();
        } catch (Exception e) {
            return null;
        }
    }

    public void removerCarro(int id) {
        try {
            carroDAO.removerPorId(id);
        } catch (Exception e) {
            System.out.print("Erro ao remover o carro!");
        }
    }

    public void atualizarCarro(int id, CarroTO carro) {
        try {
            carroDAO.atualizarPorId(id, carro);
        } catch (Exception e) {
            System.out.print("Erro ao atualizar o carro!");
        }
    }
}
