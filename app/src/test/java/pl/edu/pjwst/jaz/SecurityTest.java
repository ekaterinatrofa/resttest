package pl.edu.pjwst.jaz;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwst.jaz.requestBody.LoginRequest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@IntegrationTest

public class SecurityTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }



    @Test
    public void whenUserIsAdminCanLogInShouldReturn200() {
        // @formatter:off
        var response = given().log().all()
                         .contentType("application/json")
                         .body(new LoginRequest("admin", "Admin"))
        .when()
                .post("http://localhost:" + port + "/api/login/")
        .then()
                .log().all()
        .statusCode(200)
                .extract().response();
        // @formatter:on
        Assert.assertTrue(response.asString().contains("admin"));
    }

    @Test
    public void whenUserIsAdminAndPasswordIsWrongShouldReturn401() {
        // @formatter:off
        var response = given().log().all()
                .contentType("application/json")
                .body(new LoginRequest("admin", "wwwwwwww"))
        .when()
                .post("http://localhost:" + port + "/api/login/")
        .then()
                .log().all()
        .statusCode(401)
                .extract().response();
        // @formatter:on
        Assert.assertFalse(response.asString().contains("admin"));
    }



    @Test
    public void whenUserHasNoRightsToAccessTheListShouldReturn403() {
        // @formatter:off
        var response = given().log().all()
                        .contentType("application/json")
                        .body(new LoginRequest("user", "qwerty"))
        .when()
                        .get("http://localhost:" + port + "/api/listUsers/")
        .then()
                        .log().all()
        .statusCode(403)
                        .extract().response();
        // @formatter:on
        assertEquals(403, response.getStatusCode());
    }


    @Test
    public void whenUserIsRegisterShouldReturn200() {
        // @formatter:off
        var response = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"username\" : \"jaz\",\n" +
                        "    \"firstName\" : \"jaz\",\n" +
                        "    \"lastName\":\"jaz\",\n" +
                        "    \"password\" : \"qwerty\",\n" +
                        "    \"role\" : [\"user\"]\n" +
                        "}")
        .when()
                .post("http://localhost:" + port + "/api/register/")
        .then()
                .log().all()
        .statusCode(200)
                .extract().response();
        // @formatter:on
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void whenUserIsRegisterAndTryToLoginShouldReturn200() {
        // @formatter:off
        var response = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"username\" : \"jaz\",\n" +
                        "    \"firstName\" : \"jaz\",\n" +
                        "    \"lastName\":\"jaz\",\n" +
                        "    \"password\" : \"qwerty\",\n" +
                        "    \"role\" : [\"user\"]\n" +
                        "}")
                .when()
                        .post("http://localhost:" + port + "/api/register/")
                        .thenReturn();


        given().log().all()

                .contentType("application/json")
                .body(new LoginRequest("jaz", "qwerty"))

                .post("http://localhost:" + port + "/api/login/")
                .then()
                .log().all()
                .statusCode(200)
        ;
        // @formatter:on
        assertEquals(200, response.getStatusCode());
    }


    @Test
    public void requestForbiddenByFilter() {
        var response = given().log().all()
                .when()
                .get("http://localhost:" + port +"/api/auth0/kate")
                .thenReturn();
        var statusCode = response.getStatusCode();

        assertThat(statusCode).isEqualTo(401);

    }


}
