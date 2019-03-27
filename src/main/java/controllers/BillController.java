package controllers;

import models.Bill;
import services.BillService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("bill")
public class BillController {

    @EJB
    private BillService service;

//    @GET
//    @Produces("application/json")
//    public Response getAll(@Context HttpServletRequest request) {
////        service.getAll();
//    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Bill bill = service.getById(id);

        return Response.ok(bill).build();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response create(Bill bill) {
//        boolean success = service.create(bill);
//    }

//    @PUT
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response update(Bill bill) {
//        boolean success = service.update(bill);
//    }

//    @DELETE
//    @Path("/{id}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response delete(@PathParam("id") Long id) {
//        boolean success = service.delete(id);
//    }
}
