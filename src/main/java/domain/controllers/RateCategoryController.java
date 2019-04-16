package domain.controllers;

import domain.models.RateCategory;
import domain.services.RateCategoryService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("ratecategory")
public class RateCategoryController {

    @EJB
    private RateCategoryService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCarTracker(RateCategory rc) {
        return Response.ok(service.create(rc)).build();
    }
}
