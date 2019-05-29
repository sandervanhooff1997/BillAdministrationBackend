package domain.controllers;

import domain.models.Road;
import domain.services.RoadService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("road")
public class RoadController {

    @EJB
    private RoadService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Road ct) {
        return Response.ok(service.create(ct)).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(Road rc) {
        return Response.ok(service.update(rc)).build();
    }

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(Long id) {
        return Response.ok(service.delete(id)).build();
    }
}
