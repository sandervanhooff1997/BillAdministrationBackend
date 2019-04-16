package domain.controllers;

import domain.models.*;
import domain.services.BillService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("bill")
public class BillController {

    @EJB
    private BillService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response changePaymenStatus (Bill bill) {
        return Response.ok(service.changePaymenStatus(bill)).build();
    }
}
