package pl.edu.pjwst.jaz;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@IntegrationTest

public class ExampleTest {
  @LocalServerPort
   int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }


  @Test
    public void should_respond_to_readiness_request() {
        var response = given()
                .when()
                .get("/api/is-ready")
                .thenReturn();
        var statusCode = response.getStatusCode();

        assertThat(statusCode).isEqualTo(200);

    }
}
