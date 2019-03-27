package controllers;

import models.*;
import services.*;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("vehicle")
public class VehicleController {
    @EJB
    private RateCategoryService service;

//    @GET
//    @Produces("application/json")
//    public Response getAll(@Context HttpServletRequest request) {
////        service.getAll();
//    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        RateCategory rateCategory = service.getById(id);

        return Response.ok(rateCategory ).build();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response create(RateCategory rateCategory ) {
//        boolean success = service.create(rateCategory );
//    }
//
//    @PUT
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response update(RateCategory rateCategory ) {
//        boolean success = service.update(rateCategory );
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response delete(@PathParam("id") Long id) {
//        boolean success = service.delete(id);
//    }
}
