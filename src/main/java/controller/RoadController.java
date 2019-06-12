package controller;

import domain.controllers.Requests.AddPriceToRoadRequest;
import domain.controllers.Requests.CreateRoadRequest;
import domain.models.Price;
import domain.models.Road;
import domain.services.PriceService;
import domain.services.RoadService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Path("road")
public class RoadController {

    @EJB
    private RoadService service;

    @EJB
    private PriceService priceService;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @POST
    @Path("/generate")
    @Produces("application/json")
    public Response generateRoads(List<String> addresses) {
        HashSet<String> distinctAddresses = new HashSet(addresses);
        Random generator = new Random();

        for (String s : distinctAddresses) {
            double number = generator.nextInt(11) / 100.0;
            Price price = new Price();
            price.setPrice(number);
            Price p = priceService.create(price);

            number = generator.nextInt(11) / 100.0;
            Price rushPrice = new Price();
            rushPrice.setPrice(number);
            Price rushP = priceService.create(rushPrice);

            service.create(s, p.getId(), rushP.getId());
        }

        return Response.status(200).build();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(CreateRoadRequest req) {
        return Response.ok(service.create(req.getName(), req.getPriceId(), req.getRushHourPriceId())).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(Road rc) {
        return Response.ok(service.update(rc)).build();
    }

    @PUT
    @Path("/addprice")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addPriceToRoad(AddPriceToRoadRequest req) {
        return Response.ok(service.addPriceToRoad(req.getRoadId(), req.getPriceId())).build();
    }
    @PUT
    @Path("/addrushprice")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addRushPriceToRoad(AddPriceToRoadRequest req) {
        return Response.ok(service.addRushPriceToRoad(req.getRoadId(), req.getPriceId())).build();
    }

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(Long id) {
        return Response.ok(service.delete(id)).build();
    }
}
