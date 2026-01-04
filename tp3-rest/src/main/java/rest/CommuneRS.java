package rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Commune;

import java.util.ArrayList;
import java.util.List;

@Path("commune")
@Produces(MediaType.APPLICATION_JSON)
public class CommuneRS {

    private static final List<Commune> communes = new ArrayList<>();

    static {
        communes.add(new Commune(93, "Villetaneuse"));
        communes.add(new Commune(75, "Paris"));
        communes.add(new Commune(94, "Vitry"));
        communes.add(new Commune(91, "Massy"));
        communes.add(new Commune(95, "Sarcelle"));
    }

    private Commune findByCp(int cp) {
        for (Commune c : communes) {
            if (c.getCodePostal() == cp) return c;
        }
        return null;
    }

    // 3) Create + return a Commune from request params
    @GET
    @Path("{codePostal}/{nom}")
    public Commune createFromParams(@PathParam("codePostal") int codePostal, @PathParam("nom") String nom) {
        return new Commune(codePostal, nom);
    }

    // GET /rest/commune/93 => returns commune json
    @GET
    @Path("{codePostal}")
    public Response get(@PathParam("codePostal") int codePostal) {
        Commune c = findByCp(codePostal);
        if (c == null) return Response.status(404).build();
        return Response.ok(c).build();
    }

    // POST /rest/commune/create => add new commune and return count
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(@FormParam("codePostal") int codePostal, @FormParam("nom") String nom) {
        communes.add(new Commune(codePostal, nom));
        return String.valueOf(communes.size());
    }

    // PUT /rest/commune/93 => update name and return updated commune json
    @PUT
    @Path("{codePostal}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("codePostal") int codePostal, @FormParam("nom") String nom) {
        Commune c = findByCp(codePostal);
        if (c == null) return Response.status(404).build();
        c.setNom(nom);
        return Response.ok(c).build();
    }

    // DELETE /rest/commune/93 => delete and return remaining count
    @DELETE
    @Path("{codePostal}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("codePostal") int codePostal) {
        Commune c = findByCp(codePostal);
        if (c == null) return Response.status(404).build();
        communes.remove(c);
        return Response.ok(String.valueOf(communes.size())).build();
    }
}
