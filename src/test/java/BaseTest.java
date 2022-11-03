import api.client.CourierClient;
import models.Courier;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    public Courier courier;

    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        courier = Courier.getRandomCourier();
    }

    @After
    public void teardown() {
        try {
            courierClient.deleteCourier(courier);
        } catch (NullPointerException err) {
        }
    }
}
