package users;

import service.Endpoints;
import service.Specifications;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserProperties {

    @Step("Удалить пользователя")
    public ValidatableResponse deleteExistingUser(String accessToken) {
        return given()
                .spec(Specifications.requestSpecification())
                .log()
                .all()
                .header("Authorization", accessToken)
                .when()
                .delete(Endpoints.PATCH_USER)
                .then().log().all();
    }

    @Step("Создать пользователя")
    public ValidatableResponse createNewUser(User user) {
        return given()
                .spec(Specifications.requestSpecification())
                .log()
                .all()
                .body(user)
                .when()
                .post(Endpoints.AUTH_PATH + "/register")
                .then()
                .log()
                .all();
    }
}

