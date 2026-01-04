package rest;

import entities.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.UserService;

import java.util.List;

@Path("user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserRS {

    @Inject
    private UserService service;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("nom") String nom, @FormParam("prenom") String prenom) {
        User u = new User();
        u.setNom(nom);
        u.setPrenom(prenom);
        long id = service.createUser(u);
        return Response.status(201).entity(String.valueOf(id)).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) {
        User u = service.findUserById(id);
        if (u == null) return Response.status(404).build();
        return Response.ok(u).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") long id, @FormParam("nom") String nom, @FormParam("prenom") String prenom) {
        User existing = service.findUserById(id);
        if (existing == null) {
            User created = new User();
            created.setNom(nom);
            created.setPrenom(prenom);
            long newId = service.createUser(created);
            return Response.status(201).entity(String.valueOf(newId)).build();
        }
        existing.setNom(nom);
        existing.setPrenom(prenom);
        service.updateUser(existing);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        if (!service.deleteUserById(id)) return Response.status(404).build();
        return Response.ok().build();
    }

    @GET
    @Path("all")
    public Response findAll() {
        List<User> list = service.findAllUser();
        if (list == null || list.isEmpty()) {
            return Response.status(404).entity("La base de donnée des users est vide").build();
        }
        return Response.ok(list).build();
    }
}
