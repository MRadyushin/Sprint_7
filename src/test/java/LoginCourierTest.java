import models.CourierCreds;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTest extends BaseTest {

    @Test
    @DisplayName("Check authorization courier with all data")
    @Description("Checking authorization courier with all data")
    public void authCourier() {
        courierClient.sendRequestAddCourier(courier);
        Response response = courierClient.returnCourierResponse(new CourierCreds(courier.getLogin(), courier.getPassword()));
        response.then().assertThat().statusCode(SC_OK)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Check fail authorization courier without password")
    @Description("Checking fail authorization courier without password")
    public void authFailCourierWithoutPassword() {
        courierClient.sendRequestAddCourier(courier);
        Response response = courierClient.returnCourierResponse(new CourierCreds(courier.getLogin(), ""));
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check fail authorization courier without login")
    @Description("Checking fail authorization courier without login")
    public void authFailCourierWithoutLogin() {
        courierClient.sendRequestAddCourier(courier);
        Response response = courierClient.returnCourierResponse(new CourierCreds("", courier.getPassword()));
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check fail authorization courier with invalid login")
    @Description("Checking fail authorization courier with invalid login")
    public void authFailCourierWithInvalidLogin() {
        Response response = courierClient.returnCourierResponse(new CourierCreds(courier.getLogin() + "/*", courier.getPassword()));
        response.then().assertThat().statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check fail authorization courier with invalid password")
    @Description("Checking fail authorization courier with invalid password")
    public void authFailCourierWithInvalidPassword() {
        courierClient.sendRequestAddCourier(courier);
        Response response = courierClient.returnCourierResponse(new CourierCreds(courier.getLogin(), courier.getPassword() + "/*"));
        response.then().assertThat().statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}