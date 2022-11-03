import api.client.OrderClient;
import models.Order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)

public class CreateOrderTest {

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    List<String> color;

    @Parameterized.Parameters(name = "Тестовые цвета: {0} {1}")

    public static List<List<String>> getColor() {
        List<List<String>> colors = new ArrayList<>();
        colors.add(List.of("BLACK"));
        colors.add(List.of("GREY"));
        colors.add(List.of("BLACK", "GREY"));
        colors.add(List.of());
        return colors;
    }

    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Check create order with any colors")
    @Description("Checking create order with any colors")
    public void createOrder() {
        Order order = Order.createOrderData(color);
        Response response = orderClient.sendRequestAddOrder(order);
        response.then().assertThat().statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());
    }
}