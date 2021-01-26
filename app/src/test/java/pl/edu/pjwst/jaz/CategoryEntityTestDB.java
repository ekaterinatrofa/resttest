package pl.edu.pjwst.jaz;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwst.jaz.requestBody.LoginRequest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@IntegrationTest

public class CategoryEntityTestDB {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }


    public Response login(String username, String password) {
        var response = given().log().all()
                .contentType("application/json")
                .body(new LoginRequest(username, password))
                .when()
                .post("http://localhost:" + port + "/api/login/")
                .thenReturn();
        return response;
    }

    public void addCategory(String categoryName) {
        given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \""+ categoryName +"\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addCategory/")
                .thenReturn();
    }

    @Test
    public void whenUserIsLoggedAsAdminAddCategoryShouldReturn200() {
        // @formatter:off
        var addCategory = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \"TV\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addCategory/")
                .thenReturn();
        // @formatter:on
        assertEquals(200, addCategory.getStatusCode());
    }

    @Test
    public void whenUserIsNotAdminAddCategoryShouldReturn403() {
        // @formatter:off
        var addCategoryResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \"TV\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addCategory/")
                .then()
                .log().all()
                .statusCode(403)
          .extract().response();
        // @formatter:on
        assertEquals(403, addCategoryResponse.getStatusCode());
    }

    @Test
    public void whenUserIsLoggedAsAdminUpdateCategoryShouldReturn200() {
        // @formatter:off
        addCategory("TV");
        var updateCategoryResponse = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"oldCategoryName\" : \"TV\",\n" +
                        "    \"newCategoryName\" : \"Kate\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateCategory/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        assertEquals(200, updateCategoryResponse.getStatusCode());
    }

    @Test
    public void whenUserIsNotAdminUpdateCategoryShouldReturn403() {
        // @formatter:off
        var updateCategoryResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"oldCategoryName\" : \"TV\",\n" +
                        "    \"newCategoryName\" : \"Kate\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateCategory/")
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();
        // @formatter:on
        assertEquals(403, updateCategoryResponse.getStatusCode());
    }

    @Test
    public void ListOfAllCategoriesIsAvailableForEveryBodyShouldReturn200() {
        // @formatter:off
        addCategory("Laptops");
        var response = given().log().all()
                .contentType("application/json")
                .when()
                .get("http://localhost:" + port + "/api/listCategories/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        Assert.assertTrue(response.asString().contains("Laptops"));
    }
}
