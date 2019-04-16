package domain.controllers;

import domain.models.*;
import domain.services.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("mockdata")
public class MockDataController {

    @EJB
    private OwnerCredentialService ownerCredentialService;
    @EJB
    private RateCategoryService rateCategoryService;
    @EJB
    private VehicleService vehicleService;
    @EJB
    private CarTrackerService carTrackerService;
    @EJB
    private BillService billService;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(true).build();
    }

    @POST
    @Produces("application/json")
    public Response createMockData() {
        OwnerCredentials oc = new OwnerCredentials();
        oc.setAccountRider(true);
        oc.setBegin(new Date());
        oc.setEnd(new Date());
        oc.setCity("Tilburg");
        oc.setHouseNumber(1);
        oc.setName("Test user 1");
        oc.setPostalCode("6501AA");
        oc.setStreetName("Straat 1");
        List<OwnerCredentials> ocs = new ArrayList<>();
        ocs.add(oc);

        ownerCredentialService.create(oc);

        RateCategory rc = new RateCategory();
        rc.setName("Rate category 1");
        rc.setPrice(0.21);

//        rateCategoryService.create(rc);

        Vehicle v = new Vehicle();
        v.setLicencePlate("XXXXXX");
        v.setRateCategory(rc);
        v.setOwnerCredentials(ocs);

//        vehicleService.create(v);

        CarTracker ct = new CarTracker();
        ct.setDeleted(false);
        ct.setHardware("Intel i7");
        ct.setMileage(250);
        v.addCarTracker(ct);

//        carTrackerService.create(ct);

        Bill b = new Bill();
        b.setPaymentStatus(PaymentStatus.OPEN);
        b.setDate(new Date());
        b.setTotalAmount(100.0);
        b.addCarTracker(ct);

        billService.create(b);

        return Response.ok(true).build();
    }
}
