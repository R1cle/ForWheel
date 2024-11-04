package resource;

import to.UsuarioTO;
import bo.UsuarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("usuarios")
public class UsuarioResource {
    UsuarioBO usuarioService = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarUsuario(UsuarioTO usuario) throws Exception {
        usuarioService.adicionarUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<UsuarioTO> usuarios = usuarioService.listarUsuarios();
        Response.ResponseBuilder response = null;
        if (usuarios == null) {
            response =  Response.status(Response.Status.NOT_FOUND).entity("Nenhum usuário encontrado.");
        } else {
            response = Response.ok();
        }
        response.entity(usuarios);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response removerUsuario(@PathParam("id") int id) {
        usuarioService.removerUsuario(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") int id, UsuarioTO usuario) {
        usuarioService.atualizarUsuario(id, usuario);
        return Response.ok(usuario).build();
    }
}
