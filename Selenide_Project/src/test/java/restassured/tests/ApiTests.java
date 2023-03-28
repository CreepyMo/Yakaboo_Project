package restassured.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import restassured.data.DataForApiTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests extends DataForApiTests {

    @Test
    public void createPetTest() {
        given()
                .header("Content-Type", "application/json")
                .body(getNewPet().toJSONString())
                .when()
                .post(getBaseURI() + "/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo(getNewPet().get("name")))
                .body("id", equalTo(getNewPet().get("id")))
                .log().all();
    }
    @Test
    public void getAvailablePetTest() {
        given()
                .body(getAvailablePet().toJSONString())
                .get(getBaseURI() + "/pet/findByStatus")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void deletePetTest() {
        when()
                .delete(getBaseURI() + "/pet/100")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void createUserTest() {
        given()
                .header("Content-Type", "application/json")
                .body(getNewUser().toJSONString())
                .when()
                .post(getBaseURI() + "/user")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void createSeveralUsersTest() {
        given()
                .header("Content-Type", "application/json")
                .body(getListOfUsers().toJSONString())
                .when()
                .post(getBaseURI() + "/user/createWithList")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void loginUserTest() {
        given()
                .auth().basic(getUsername(), getPassword())
                .get(getBaseURI() + "/user/login")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void deleteUserTest() { //works only for logged-in user
        given()
                .auth().basic(getUsername(), getPassword())
                .get(getBaseURI() + "/user/login");

        when()
                .delete(getBaseURI() + "/user/creepymonster")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void updateUserTest() { //works only for logged-in user
        given()
                .auth().basic(getUsername(), getPassword())
                .get(getBaseURI() + "/user/login");

        given()
                .header("Content-Type", "application/json")
                .body(getUpdatedUser().toJSONString())
                .when()
                .put(getBaseURI() + "/user/creepymonster")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void createOrderTest() {
        given()
                .header("Content-Type", "application/json")
                .body(getNewOrder().toJSONString())
                .when()
                .post(getBaseURI() + "/store/order")
                .then()
                .statusCode(200)
                .body("status", equalTo("placed")).log().all();
    }

    @Test
    public void deleteOrderTest() {
        when()
                .delete(getBaseURI() + "/store/order/1")
                .then()
                .statusCode(200).log().all();
    }


}
