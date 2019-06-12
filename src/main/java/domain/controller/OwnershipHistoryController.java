package domain.controller;

import domain.controller.Requests.CreateOwnershipHistoryRequest;
import domain.models.OwnershipHistory;
import domain.services.OwnershipHistoryService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("ownershiphistory")
public class OwnershipHistoryController {

    @EJB
    private OwnershipHistoryService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{licensePlate}")
    @Produces("application/json")
    public Response getAllByLicensePlate(@PathParam("licensePlate") String licensePlate) {
        return Response.ok(service.getAllByLicensePlate(licensePlate)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(CreateOwnershipHistoryRequest request) {
        return Response.ok(service.create(request.getVehicleId(), request.getOwnerCredentialsId())).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(OwnershipHistory ownershipHistory) {
        return Response.ok(service.update(ownershipHistory)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(@PathParam(value = "id") Long id) {
        return Response.ok(service.delete(id)).build();
    }
}
