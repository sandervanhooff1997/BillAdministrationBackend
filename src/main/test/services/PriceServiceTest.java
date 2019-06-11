package services;

import domain.models.Price;
import domain.services.PriceService;
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
public class PriceServiceTest {

    private PriceService priceService;

    @Before
    public void setUp() {
        priceService = mock(PriceService.class);
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Test
    public void getAll() {
        List<Price> expected = new ArrayList<>();

        when(this.priceService.getAll()).thenReturn(expected);

        List<Price> actual = this.priceService.getAll();

        verify(this.priceService).getAll();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUnused() {
        List<Price> expected = new ArrayList<>();

        when(this.priceService.getAllUnused()).thenReturn(expected);

        List<Price> actual = this.priceService.getAllUnused();

        verify(this.priceService).getAllUnused();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() {

        Price expected = new Price();

        when(this.priceService.create(Matchers.any(Price.class))).thenReturn(expected);

        Price actual = this.priceService.create(expected);

        verify(this.priceService).create(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Price price = new Price();

        Boolean expected = true;

        when(this.priceService.update(Matchers.any(Price.class))).thenReturn(true);

        Boolean actual = this.priceService.update(price);

        verify(this.priceService).update(price);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        Boolean expected = true;

        when(this.priceService.delete(Matchers.anyLong())).thenReturn(true);

        Boolean actual = this.priceService.delete(new Long(1));

        verify(this.priceService).delete(new Long(1));

        Assert.assertEquals(expected, actual);
    }
}
