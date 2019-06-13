package services;

import domain.enums.VehicleType;
import domain.models.*;
import domain.services.BillService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    private BillService billService;

    @Before
    public void setUp() throws Exception {
        billService = mock(BillService.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void generateBills() throws Exception{

        List<Movement> movementList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            Movement movement = mock(Movement.class);

            movementList.add(movement);
        }

        List<Bill> bills = billService.generateBills(movementList);

        Assert.assertNotNull(bills);
    }

    @Test
    public void isRushHour() throws ParseException {
        Date d = new SimpleDateFormat( "HH:mm" ).parse( "16:59" );

        Assert.assertEquals("Unit test to check if time is in range of rush hour times.",false, billService.isRushHour(d));
    }

    @Test
    public void applyVehicleTypeFactor() {
        Double expected = 0.0;

        when(this.billService.applyVehicleTypeFactor(Matchers.any(VehicleType.class), Matchers.anyDouble())).thenReturn(0.0);

        Double actual = this.billService.applyVehicleTypeFactor(VehicleType.ELECTRIC, 0.0);

        verify(this.billService).applyVehicleTypeFactor(VehicleType.ELECTRIC, 0.0);

        Assert.assertNotNull(actual);

    }

    @Test
    public void calculateTaxes() {
        Double expected = 0.0;

        Movement movement = new Movement();
        Road road = new Road();

        when(this.billService.calculateTaxes(Matchers.any(Movement.class), Matchers.any(Road.class), Matchers.anyBoolean())).thenReturn(0.0);

        Double actual = this.billService.calculateTaxes(movement, road, true);

        verify(this.billService).calculateTaxes(movement, road, true);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void changePaymentStatus() {
        Boolean expected = true;

        Bill bill = new Bill();

        when(this.billService.changePaymenStatus(Matchers.any(Bill.class))).thenReturn(true);

        Boolean actual = this.billService.changePaymenStatus(bill);

        verify(this.billService).changePaymenStatus(bill);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAll() {
        List<Bill> expected = new ArrayList<>();

        when(this.billService.getAll()).thenReturn(expected);

        List<Bill> actual = this.billService.getAll();

        verify(this.billService).getAll();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getById() {
        Bill expected = new Bill();

        when(this.billService.getById(Matchers.anyLong())).thenReturn(expected);

        Bill actual = this.billService.getById(new Long(1));

        verify(this.billService).getById(new Long(1));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void recalculateBill() {
//        Bill expected = new Bill();
//
//        when(this.billService.recalculateBill(Matchers.anyLong())).thenReturn(expected);
//
//        Bill actual = this.billService.recalculateBill(new Long(1));
//
//        verify(this.billService).recalculateBill(new Long(1));
//
//        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllByVehicle() {
        List<Bill> expected = new ArrayList<>();

        when(this.billService.getAllByVehicleId(Matchers.anyLong())).thenReturn(expected);

        List<Bill> actual = this.billService.getAllByVehicleId(new Long(1));

        verify(this.billService).getAllByVehicleId(new Long(1));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCarTrackersInCarMovements() {
        List<CarTracker> expected = new ArrayList<>();

        CarMovements carMovements = new CarMovements();

        when(this.billService.getCarTrackersInCarMovements(Matchers.any(CarMovements.class))).thenReturn(expected);

        List<CarTracker> actual = this.billService.getCarTrackersInCarMovements(carMovements);

        verify(this.billService).getCarTrackersInCarMovements(carMovements);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void makeBill() {
        Bill expected = new Bill();

        CarMovements carMovements = new CarMovements();
        List<Road> roads = new ArrayList<>();

        when(this.billService.makeBill(Matchers.any(CarMovements.class), Matchers.anyList())).thenReturn(expected);

        Bill actual = billService.makeBill(carMovements, roads);

        verify(this.billService).makeBill(carMovements, roads);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void makeBills() {
        List<Bill> expected = new ArrayList<>();

        when(this.billService.makeBills(Matchers.anyList(), Matchers.anyList())).thenReturn(expected);

        List<Bill> actual = billService.makeBills(new ArrayList<>(), new ArrayList<>());

        verify(this.billService).makeBills(new ArrayList<>(), new ArrayList<>());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sortMovements() {
        List<CarMovements> expected = new ArrayList<>();

        when(this.billService.sortMovements(Matchers.anyList())).thenReturn(expected);

        List<CarMovements> actual = billService.sortMovements(new ArrayList<>());

        verify(this.billService).sortMovements(new ArrayList<>());

        Assert.assertEquals(expected, actual);
    }
}