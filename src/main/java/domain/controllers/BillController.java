package domain.controllers;

import domain.models.*;
import domain.models.Bill;
import domain.services.BillService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("bill")
public class BillController {

    @EJB
    private BillService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}/recalculate")
    @Produces("application/json")
    public Response recalculateBill(@PathParam("id") Long id) {
        return Response.ok(service.recalculateBill(id)).build();
    }

    @GET
    @Path("vehicle/{vehicleId}")
    @Produces("application/json")
    public Response getAllByVehicleId(@PathParam(value = "vehicleId") Long vehicleId) {
        return Response.ok(service.getAllByVehicleId(vehicleId)).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response changePaymenStatus (Bill bill) {
        return Response.ok(service.changePaymenStatus(bill)).build();
    }

    @POST
    @Path("/generate")
    @Consumes("application/json")
    @Produces("application/json")
    public Response generateBills(List<Movement> movements) {
        try {
            return Response.ok(service.generateBills(movements)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
