package controllers;

import models.OwnerCredentials;
import services.OwnerCredentialService;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("ownercredential")
public class OwnerCredentialController {
    @EJB
    private OwnerCredentialService service;

//    @GET
//    @Produces("application/json")
//    public Response getAll(@Context HttpServletRequest request) {
////        service.getAll();
//    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        OwnerCredentials ownerCredentials = service.getById(id);

        return Response.ok(ownerCredentials).build();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response create(OwnerCredentials ownerCredentials) {
//        boolean success = service.create(ownerCredentials);
//    }
//
//    @PUT
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response update(OwnerCredentials ownerCredentials) {
//        boolean success = service.update(ownerCredentials);
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
