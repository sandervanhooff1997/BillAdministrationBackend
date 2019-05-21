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
    private List<Movement> testMovements = new ArrayList<>();
    private Double totalPrice = 0.0;

    @Before
    public void setUp() throws Exception {

        billService = new BillService();
        vehicleService = new VehicleService();
        carTrackerService = new CarTrackerService();
        countryService = new CountryService();

        Road road = new Road();
        road.setName("A2");
        road.setPricePerKilometer(1.15);
        Road road2 = new Road();
        road.setName("A1");
        road.setPricePerKilometer(3.15);

        countryService.addRoadToCountry(road);
        countryService.addRoadToCountry(road2);

        Movement m1 = new Movement();
        m1.setCoord(new Point2D.Double(1.15, -54.146));
        String dateValue1 = "31-Dec-2019 16:59:00";
        m1.setDate(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(dateValue1));
        m1.setRoad("A33");
        testMovements.add(m1);

        Movement m2 = new Movement();
        m2.setCoord(new Point2D.Double(3.15, -89.146));
        String dateValue2 = "31-Dec-2019 18:59:00";
        m2.setDate(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(dateValue2));
        m2.setRoad("A1");
        testMovements.add(m2);

        Movement m3 = new Movement();
        m3.setCoord(new Point2D.Double(7.15, -89.146));
        String dateValue3 = "31-Dec-2019 18:59:00";
        m3.setDate(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(dateValue2));
        m3.setRoad("N12");
        testMovements.add(m3);

        //TODO: Movementobjects hierin aanmaken.
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void MovementOnSpecificRoad() throws ParseException {

        Vehicle testCar = new Vehicle();
        List<CarTracker> carTrackers = new ArrayList<>();
        CarTracker ct1 = new CarTracker();
        CarTracker ct2 = new CarTracker();
        carTrackers.add(ct1);
        carTrackers.add(ct2);
        testCar.setCarTrackers(carTrackers);

        CarTracker testCarTracker = testCar.getCarTracker();

        testCarTracker.setMovements(testMovements);

        for (Road road : countryService.getRoads()) {
            List<Movement> testMovementsList = testCarTracker.getMovements();
            for (Movement movement : testMovementsList) {
                 if (movement.getRoad().equals(road.getName())) {
                     totalPrice  = totalPrice + road.getPricePerKilometer();
                 }
            }
        }

        Assert.assertEquals("Test to see if movements are on an specific road ", new Double(3.15), totalPrice);

        totalPrice = 0.0;
    }

    @Test
    public void MovementInRushHour() {

        Vehicle testCar = new Vehicle();
        List<CarTracker> carTrackers = new ArrayList<>();
        CarTracker ct1 = new CarTracker();
        CarTracker ct2 = new CarTracker();
        carTrackers.add(ct1);
        carTrackers.add(ct2);
        testCar.setCarTrackers(carTrackers);

        CarTracker testCarTracker = testCar.getCarTracker();

        testCarTracker.setMovements(testMovements);

        List<Movement> testMovementsList = testCarTracker.getMovements();

        for (Movement m : testMovementsList) {
            totalPrice = totalPrice + countryService.getRushHourRate(m);
        }

        Assert.assertEquals("Test to see if movement time is within rush hour range ", new Double(0.2), totalPrice);

        totalPrice = 0.0;
    }

    public void DistanceOnSpecificRoad2() {

        Vehicle testCar = new Vehicle();
        List<CarTracker> carTrackers = new ArrayList<>();
        CarTracker ct1 = new CarTracker();
        CarTracker ct2 = new CarTracker();
        carTrackers.add(ct1);
        carTrackers.add(ct2);
        testCar.setCarTrackers(carTrackers);

        CarTracker testCarTracker = testCar.getCarTracker();

        testCarTracker.setMovements(testMovements);

        for (Road road : countryService.getRoads()) {
            List<Movement> testMovementsList = testCarTracker.getMovements();
            for (Movement movement : testMovementsList) {
                if (movement.getRoad().equals(road.getName())) {
                    totalPrice  = totalPrice + road.getPricePerKilometer();
                }
            }
        }

        Assert.assertEquals("Test to see if movements are on an specific road ", new Double(3.15), totalPrice);

        totalPrice = 0.0;
    }

    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return calendar.getTime();
    }
}
