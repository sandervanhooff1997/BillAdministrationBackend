package domain.controllers;

import domain.models.auth.Employee;
import domain.services.auth.AuthenticationService;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("authentication")
public class AuthenticationController {
    @EJB
    private AuthenticationService service;

    @Path("/register")
    @POST
    public Response register(Employee employee)
    {
        try {
            service.register(employee);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/login")
    @POST
    public Response login(Employee employee) {
        try {
            String token = service.login(employee);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
