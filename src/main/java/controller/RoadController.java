package controller;

import domain.controllers.Requests.AddPriceToRoadRequest;
import domain.controllers.Requests.CreateRoadRequest;
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
    public Response create(CreateRoadRequest req) {
        return Response.ok(service.create(req.getName(), req.getPriceId(), req.getRushHourPriceId())).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(Road rc) {
        return Response.ok(service.update(rc)).build();
    }

    @PUT
    @Path("/addprice")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addPriceToRoad(AddPriceToRoadRequest req) {
        return Response.ok(service.addPriceToRoad(req.getRoadId(), req.getPriceId())).build();
    }
    @PUT
    @Path("/addrushprice")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addRushPriceToRoad(AddPriceToRoadRequest req) {
        return Response.ok(service.addRushPriceToRoad(req.getRoadId(), req.getPriceId())).build();
    }

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(Long id) {
        return Response.ok(service.delete(id)).build();
    }
}
