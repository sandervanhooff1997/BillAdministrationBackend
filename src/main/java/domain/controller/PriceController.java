package domain.controller;

import domain.controller.Requests.CreateOrUpdateDefaultPriceRequest;
import domain.models.Price;
import domain.services.PriceService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("price")
public class PriceController {

    @EJB
    private PriceService service;

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
    @Path("/default")
    @Produces("application/json")
    public Response getDefault() {
        return Response.ok(service.getDefaultPrice()).build();
    }

    @GET
    @Path("/defaultrush")
    @Produces("application/json")
    public Response getDefaultRush() {
        return Response.ok(service.getDefaultRushPrice()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Price price) {
        return Response.ok(service.create(price)).build();
    }

    @POST
    @Path("/default")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createOrUpdateDefaultPrice(CreateOrUpdateDefaultPriceRequest req) {
        return Response.ok(service.createOrUpdateDefaultPrice(req.getPrice(), req.isRushPrice())).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(Price price) {
        return Response.ok(service.update(price)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(@PathParam(value = "id") Long id) {
        return Response.ok(service.delete(id)).build();
    }
}
