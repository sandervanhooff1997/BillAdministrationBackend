package domain.controller;

import domain.models.OwnerCredentials;
import domain.services.OwnerCredentialService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("ownercredentials")
public class OwnerCredentialsController {

    @EJB
    private OwnerCredentialService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/unused")
    @Produces("application/json")
    public Response getAllUnused() {
        return Response.ok(service.getAllUnused()).build();
    }

    @GET
    @Path("/unused/{vehicleId}")
    @Produces("application/json")
    public Response getAllUnusedByVehicleId(@PathParam(value = "vehicleId") Long vehicleId) {
        return Response.ok(service.getAllUnusedByVehicleId(vehicleId)).build();
    }

    @GET
    @Path("/{bsn}/{postalCode}")
    @Produces("application/json")
    public Response getByBsnAndPostalCode(@PathParam(value = "bsn") Long bsn, @PathParam(value = "postalCode") String postalCode) {
        return Response.ok(service.getByBsnAndPostalCode(bsn, postalCode)).build();
    }

    @GET
    @Path("/{bsn}")
    @Produces("application/json")
    public Response getByBsn(@PathParam(value = "bsn") Long bsn) {
        return Response.ok(service.getByBsn(bsn)).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(OwnerCredentials oc) {
        return Response.ok(service.update(oc)).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(OwnerCredentials oc) {
        return Response.ok(service.create(oc)).build();
    }
}
