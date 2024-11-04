package resource;

import to.CarroTO;
import bo.CarroBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("carros")
public class CarroResource {

    @Inject
    private CarroBO carroService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCarro(CarroTO carro) {
        if (carro == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O carro não pode ser nulo").build();
        }

        try {
            carroService.adicionarCarro(carro);
            return Response.status(Response.Status.CREATED).entity(carro).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar carro: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCarros() {
        try {
            List<CarroTO> listaCarros = carroService.listarCarros();
            if (listaCarros == null || listaCarros.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhum carro encontrado.").build();
            }
            return Response.ok(listaCarros).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar carros: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removerCarro(@PathParam("id") int id) {
        try {
            carroService.removerCarro(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return Response.status(Response.Status.NOT_FOUND).entity("Erro ao remover carro: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCarro(@PathParam("id") int id, CarroTO carro) {
        try {
            carroService.atualizarCarro(id, carro);
            return Response.ok(carro).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return Response.status(Response.Status.NOT_FOUND).entity("Erro ao atualizar carro: " + e.getMessage()).build();
        }
    }
}
