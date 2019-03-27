package controllers;

import models.CarTracker;
import services.CarTrackerService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("cartracker")
public class CarTrackerController {
    @EJB
    private CarTrackerService service;

//    @GET
//    @Produces("application/json")
//    public javax.ws.rs.core.Response getAll(@Context HttpServletRequest request) {
//        service.getAll();
//    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public javax.ws.rs.core.Response getById(@PathParam("id") Long id) {
        CarTracker carTracker = service.getById(id);

        return javax.ws.rs.core.Response.ok(carTracker).build();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public javax.ws.rs.core.Response create(CarTracker carTracker) {
//        boolean success = service.create(carTracker);
//    }

//    @PUT
//    @Consumes("application/json")
//    @Produces("application/json")
//    public javax.ws.rs.core.Response update(CarTracker carTracker) {
//        boolean success = service.update(carTracker);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public javax.ws.rs.core.Response delete(@PathParam("id") Long id) {
//        boolean success = service.delete(id);
//    }
}
