package rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Calculator;

@Path("/")
public class CalculatorRest {

    @GET
    @Path("add/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@PathParam("a") int a, @PathParam("b") int b) {
        return String.valueOf(a + b);
    }

    @GET
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    public String add1(@QueryParam("a") int a, @QueryParam("b") int b) {
        return String.valueOf(a + b);
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Calculator add2(@FormParam("a") int a, @FormParam("b") int b) {
        return new Calculator().add(a, b);
    }

    @POST
    @Path("multiply")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Calculator multiply(@FormParam("a") int a, @FormParam("b") int b) {
        return new Calculator().multiply(a, b);
    }

    @POST
    @Path("divise")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response divise(@FormParam("a") int a, @FormParam("b") int b) {
        if (b == 0) {
            return Response.status(400).type(MediaType.TEXT_PLAIN).entity("DENOMINATEUR EST NULL").build();
        }
        return Response.ok(new Calculator().divise(a, b)).build();
    }
}
