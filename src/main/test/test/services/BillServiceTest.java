package test.services;

import domain.models.CarTracker;
import domain.models.Movement;
import domain.models.Road;
import domain.models.Vehicle;
import domain.services.BillService;
import domain.services.CarTrackerService;
import domain.services.CountryService;
import domain.services.VehicleService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BillServiceTest {

    private BillService billService;
    private VehicleService vehicleService;
    private CarTrackerService carTrackerService;
    private CountryService countryService;

    @Before
    public void setUp() throws Exception {
        billService = new BillService();
        vehicleService = new VehicleService();
        carTrackerService = new CarTrackerService();
        countryService = new CountryService();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void generateBill() {

        Vehicle testCar = new Vehicle();

        List<CarTracker> carTrackers = new ArrayList<>();
        CarTracker ct1 = new CarTracker();
        CarTracker ct2 = new CarTracker();

        carTrackers.add(ct1);
        carTrackers.add(ct2);

        testCar.setCarTrackers(carTrackers);

        CarTracker testCarTracker = testCar.getCarTracker();

        List<Movement> testMovements = new ArrayList<>();

        Random rnd = new Random();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;


        Double totalPrice = 0.0;

        /**
         *
         * Create 100 random Movement objects with movement and date
         */
        for (int i = 0; i < 100; i++) {
            Movement movement = new Movement();
            movement.setCoord(new Point2D.Double(rnd.nextDouble(), rnd.nextDouble()));
            Date date = addHoursToJavaUtilDate(new Date(), ThreadLocalRandom.current().nextInt(11, 23));

            try {
                movement.setDate(dateFormat.parse(dateFormat.format(date)));
                totalPrice = totalPrice + countryService.getRushHourRate(movement);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            testMovements.add(movement);
        }

        testCarTracker.setMovements(testMovements);

        Road road = new Road();
        road.setName("A2");
        road.setPricePerKilometer(1.15);
        Road road2 = new Road();
        road.setName("A1");
        road.setPricePerKilometer(3.15);

        countryService.addRoadToCountry(road);
        countryService.addRoadToCountry(road2);

        System.out.println(totalPrice);
        Assert.assertEquals(100, testMovements.size());
    }

    public Date addHoursToJavaUtilDate(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    return calendar.getTime();
    }
}
