package controller;

import controller.Requests.CreateVehicleRequest;
import controller.Requests.MarkVehicleAsStolenRequest;
import controller.Requests.TransferOwenershipVehicleRequest;
import controller.Requests.ChangeCarTrackerVehicleRequest;
import domain.models.Vehicle;
import domain.services.VehicleService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vehicle")
public class VehicleController {

    @EJB
    private VehicleService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/licenceplates/stolen")
    @Produces("application/json")
    public Response getAllStolenLicencePlates() {
        return Response.ok(service.getAllStolenLicencePlates()).build();
    }

    @GET
    @Path("/licenceplates")
    @Produces("application/json")
    public Response getAllLicencePlates() {
        return Response.ok(service.getAllLicencePlates()).build();
    }

    @GET
    @Path("/{licencePlate}")
    @Produces("application/json")
    public Response getByLicencePlate(@PathParam(value="licencePlate") String licencePlate) {
        return Response.ok(service.getByLicencePlate(licencePlate)).build();
    }

    @GET
    @Path("/ownercredentials/{id}")
    @Produces("application/json")
    public Response getByOwnerCredentialsId(@PathParam(value="id") Long id) {
        return Response.ok(service.getByOwnerCredentialsId(id)).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(Vehicle v) {
        return Response.ok(service.update(v)).build();
    }

    @PUT
    @Path("/transferownership")
    @Produces("application/json")
    public Response transferOwnership(TransferOwenershipVehicleRequest req) {
        return Response.ok(service.transferOwnership(req.getVehicleId(), req.getOcId())).build();
    }

    @PUT
    @Path("/changecartracker")
    @Produces("application/json")
    public Response changeCarTracker(ChangeCarTrackerVehicleRequest req) {
        return Response.ok(service.changeCarTracker(req.getVehicleId(), req.getCtId())).build();
    }

    @PUT
    @Path("/stolen")
    @Produces("application/json")
    public Response update(MarkVehicleAsStolenRequest req) {
        boolean success = service.markAsStolen(req.getLicencePlate());

        return Response.ok(success).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(CreateVehicleRequest request) {
        return Response.ok(service.create(request.getLicencePlate(), request.getVehicleType(), request.getCarTrackerId(), request.getOwnerCredentialsId())).build();
    }
}
