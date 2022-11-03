package api.client;

import models.*;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String ORDER = "/api/v1/orders";

    // базовый
    private RequestSpecification getHeader() {
        return given()
                .baseUri(BASE_URI)
                .header("Content-type", "application/json");
    }

    @Step("Add order")
    public Response sendRequestAddOrder(Order order) {
        return sendPostRequest(order, ORDER);
    }

    // метод для "Отправить POST запрос":
    private Response sendPostRequest(Object body, String post) {
        Response response =
                getHeader() // заполни header
                        .body(body) // заполни body
                        .when()
                        .post(post); // отправь запрос на ручку
        return response;
    }

    // метод для "Отправить GET запрос":
    private Response sendGetRequest(String get) {
        Response response =
                getHeader() // заполни header
                        .get(get); // отправь запрос на ручку
        return response;
    }

    public Response sendRequestGetOrders() {
        return sendGetRequest(ORDER);
    }

}