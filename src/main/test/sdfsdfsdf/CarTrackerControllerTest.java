package sdfsdfsdf;

import controller.CarTrackerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CarTrackerControllerTest {


    CarTrackerController carTrackerController;

    @Before
    public void setUp() throws Exception {
        this.carTrackerController = new CarTrackerController();
        this.carTrackerController = mock(CarTrackerController.class);
    }

    @Test
    public void ShouldGetAids(){
    }

}