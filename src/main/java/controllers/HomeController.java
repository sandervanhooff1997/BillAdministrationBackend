package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class HomeController {

    @GET
    public String getTextResponseTypeDefined() {
        return  "This is a plain text sdfsdfs";
    }
}
