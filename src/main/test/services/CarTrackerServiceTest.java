package services;

import domain.models.CarTracker;
import domain.services.CarTrackerService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarTrackerServiceTest {

    private CarTrackerService carTrackerService;

    @Before
    public void setUp() {
        carTrackerService = mock(CarTrackerService.class);
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Test
    public void getAll() {
        List<CarTracker> expected = new ArrayList<>();

        when(this.carTrackerService.getAll()).thenReturn(expected);

        List<CarTracker> actual = this.carTrackerService.getAll();

        verify(this.carTrackerService).getAll();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUnused() {
        List<CarTracker> expected = new ArrayList<>();

        when(this.carTrackerService.getAllUnused()).thenReturn(expected);

        List<CarTracker> actual = this.carTrackerService.getAllUnused();

        verify(this.carTrackerService).getAllUnused();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllNotDeleted() {
        List<CarTracker> expected = new ArrayList<>();

        when(this.carTrackerService.getAllNotDeleted()).thenReturn(expected);

        List<CarTracker> actual = this.carTrackerService.getAllNotDeleted();

        verify(this.carTrackerService).getAllNotDeleted();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getById() {
        CarTracker expected = new CarTracker();

        when(this.carTrackerService.getById(Matchers.anyLong())).thenReturn(expected);

        CarTracker actual = this.carTrackerService.getById(new Long(1));

        verify(this.carTrackerService).getById(new Long(1));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Boolean expected = true;

        CarTracker carTracker = new CarTracker();

        when(this.carTrackerService.create(Matchers.any(CarTracker.class))).thenReturn(true);

        Boolean actual = this.carTrackerService.create(carTracker);

        verify(this.carTrackerService).create(carTracker);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Boolean expected = true;

        CarTracker carTracker = new CarTracker();

        when(this.carTrackerService.update(Matchers.any(CarTracker.class))).thenReturn(true);

        Boolean actual = this.carTrackerService.update(carTracker);

        verify(this.carTrackerService).update(carTracker);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        Boolean expected = true;

        when(this.carTrackerService.delete(Matchers.anyLong())).thenReturn(true);

        Boolean actual = this.carTrackerService.delete(new Long(1));

        verify(this.carTrackerService).delete(new Long(1));

        Assert.assertEquals(expected, actual);
    }
}
