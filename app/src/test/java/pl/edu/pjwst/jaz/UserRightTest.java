package pl.edu.pjwst.jaz;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@IntegrationTest
public class UserRightTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }



    @Test
    public void when() {
        var response =
                given().log().all()
                        .when()
                        .contentType("application/json")
                        .body(new LoginRequest("admin", "Admin"))
                        .post("http://localhost:" + port + "/api/login/")
                        .thenReturn();

                // @formatter:off
                given().log().all()
                        .cookies(response.getCookies())
                        //.contentType("application/json")
                        //.body(new LoginRequest("admin", "Admin"))

                        .get("http://localhost:" + port + "/api/listUsers/")
                        .then()
                        .log().all()
                        .statusCode(200)
                        ;
        // @formatter:on
        assertEquals(200, response.getStatusCode());
    }


}
