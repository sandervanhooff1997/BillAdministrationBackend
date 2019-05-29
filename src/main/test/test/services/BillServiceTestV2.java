package test.services;

import domain.models.*;
import domain.services.BillService;
import domain.services.VehicleService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class BillServiceTestV2 {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createBill() {
        BillService billService = new BillService();

        Vehicle v = new Vehicle();

        CarTracker c = new CarTracker();
        c.setId(1L);
        v.addCarTracker(c);

        // create 10 roads
        List<Road> roads = new ArrayList<>();
        for (int i = 0;i < 10; i++) {
            Road r = new Road();
            r.setName("road " + i);
            roads.add(r);
        }

        // create a price for each road
        for (int i = 0;i < 10; i++) {
            Price p = new Price();
            p.setBegin(new Date());
            p.setPrice(0.3);
            roads.get(i).setPrices(Arrays.asList(p));



            if (i == 0){
                List<Price> prices = new ArrayList<>();
                p = new Price();
                p.setBegin(new Date());
                p.setPrice(0.4);
                p.setEnd(new Date());

                Price p2 = new Price();
                p2.setBegin(new Date());
                p2.setPrice(0.5);

                prices.add(p);
                prices.add(p2);

                roads.get(i).setRushPrices(prices);
            } else {
                p = new Price();
                p.setBegin(new Date());
                p.setPrice(0.4);
                roads.get(i).setRushPrices(Arrays.asList(p));
            }
        }

        // create a movement for each road
        List<Movement> movements = new ArrayList<>();
        for (int i = 0;i < 10; i++) {
            Movement m = new Movement();
            m.setAddress("road " + i);
            m.setDate(addHoursToJavaUtilDate(new Date(), i));
            m.setCarTrackerId(1L);
            m.setDistance(1D);
            movements.add(m);
        }

        // generate the bill
        try {
            billService.generateBills(movements);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return calendar.getTime();
    }
}
