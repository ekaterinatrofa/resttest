package pl.edu.pjwstk.jaz;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@IntegrationTest
@SpringBootApplication
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
        //System.out.println(statusCode);


        assertThat(statusCode).isEqualTo(200);

    }
}
