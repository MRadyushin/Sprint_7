import models.*;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends BaseTest{

    @Test
    @DisplayName("Check add courier with all data")
    @Description("Checking add courier with all data")
    public void createNewCourier() {
        Response response = courierClient.sendRequestAddCourier(courier);
        response.then().assertThat().statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Check add duplicate courier")
    @Description("Checking add duplicate courier")
    public void createDuplicateCourier() {
        courierClient.sendRequestAddCourier(courier);
        Response response = courierClient.sendRequestAddCourier(courier);
        response.then().assertThat().statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Check add courier without login")
    @Description("Checking add courier without login")
    public void createWithoutLogin() {
        courier = Courier.getRandomCourierWithoutLogin();
        Response response = courierClient.sendRequestAddCourier(courier);
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check add courier without password")
    @Description("Checking add courier without password")
    public void createWithoutPassword() {
        courier = Courier.getRandomCourierWithoutPassword();
        Response response = courierClient.sendRequestAddCourier(courier);
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check add courier without first name")
    @Description("Checking add courier without first name")
    public void createWithoutFirstName() {
        courier = Courier.getRandomCourierWithoutFirstName();
        Response response = courierClient.sendRequestAddCourier(courier);
        response.then().assertThat().statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }


}
