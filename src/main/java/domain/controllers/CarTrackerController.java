package domain.controllers;

import domain.models.CarTracker;
import domain.services.CarTrackerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("cartracker")
public class CarTrackerController {

    @EJB
    private CarTrackerService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(CarTracker ct) {
        return Response.ok(service.create(ct)).build();
    }
}
