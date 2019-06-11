package services;

import domain.models.OwnershipHistory;
import domain.models.Price;
import domain.services.OwnershipHistoryService;
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
public class OwnershipHistoryServiceTest {

    private OwnershipHistoryService ownershipHistoryService;

    @Before
    public void setUp() {
        this.ownershipHistoryService = mock(OwnershipHistoryService.class);
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Test
    public void getAll() {
        List<OwnershipHistory> expected = new ArrayList<>();

        when(this.ownershipHistoryService.getAll()).thenReturn(expected);

        List<OwnershipHistory> actual = this.ownershipHistoryService.getAll();

        verify(this.ownershipHistoryService).getAll();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllByLicensePlate() {
        List<OwnershipHistory> expected = new ArrayList<>();

        when(this.ownershipHistoryService.getAllByLicensePlate(Matchers.anyString())).thenReturn(expected);

        List<OwnershipHistory> actual = this.ownershipHistoryService.getAllByLicensePlate("");

        verify(this.ownershipHistoryService).getAllByLicensePlate("");

        Assert.assertEquals(expected, actual);
    }
}
