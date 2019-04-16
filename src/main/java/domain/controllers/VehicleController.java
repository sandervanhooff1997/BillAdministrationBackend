package domain.controllers;

import domain.controllers.Requests.CreateVehicleRequest;
import domain.models.Vehicle;
import domain.services.VehicleService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("vehicle")
public class VehicleController {

    @EJB
    private VehicleService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(CreateVehicleRequest request) {
        return Response.ok(service.create(request.getLicencePlate(), request.getRateCategoryId(), request.getCarTrackerId())).build();
    }
}
