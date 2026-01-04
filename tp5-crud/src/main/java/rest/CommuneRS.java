package rest;

import ejb.CommuneEJB;
import entities.Commune;
import jakarta.ejb.EJB;
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

import java.util.List;

@Path("commune")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CommuneRS {

    @EJB
    private CommuneEJB ejb;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("codePostal") int codePostal, @FormParam("nom") String nom) {
        Commune c = new Commune();
        c.setCodePostal(codePostal);
        c.setNom(nom);
        long id = ejb.createCommune(c);
        return Response.status(201).entity(String.valueOf(id)).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) {
        Commune c = ejb.findCommuneById(id);
        if (c == null) return Response.status(404).build();
        return Response.ok(c).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") long id, @FormParam("codePostal") int codePostal, @FormParam("nom") String nom) {
        Commune existing = ejb.findCommuneById(id);
        if (existing == null) {
            Commune created = new Commune();
            created.setCodePostal(codePostal);
            created.setNom(nom);
            long newId = ejb.createCommune(created);
            return Response.status(201).entity(String.valueOf(newId)).build();
        }
        existing.setCodePostal(codePostal);
        existing.setNom(nom);
        ejb.updateCommune(existing);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        if (!ejb.deleteCommuneById(id)) return Response.status(404).build();
        return Response.ok().build();
    }

    @GET
    @Path("all")
    public Response findAll() {
        List<Commune> list = ejb.findAllCommune();
        if (list == null || list.isEmpty()) {
            return Response.status(404).entity("La base de donnée des communes est vide").build();
        }
        return Response.ok(list).build();
    }
}
