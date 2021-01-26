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

public class SubCategoryEntityTestDB {
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
    public void addSubCategory(String categoryName, String subCategoryName) {
                 given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \""+categoryName+"\",\n" +
                        "    \"subCategoryName\" : \""+subCategoryName+"\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addSubCategory/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void whenUserIsLoggedAsAdminAddSubCategoryShouldReturn200() {
        // @formatter:off
        addCategory("Phones");
        var addSubCategoryResponse = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \"Phones\",\n" +
                        "    \"subCategoryName\" : \"Apple\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addSubCategory/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        assertEquals(200, addSubCategoryResponse.getStatusCode());
    }

    @Test
    public void whenUserIsNotAdminAddSubCategoryShouldReturn403() {
        // @formatter:off
        var addCategoryResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \"Phones\",\n" +
                        "    \"subCategoryName\" : \"Apple\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addSubCategory/")
                .then()
                .log().all()
                .statusCode(403)
          .extract().response();
        // @formatter:on
        assertEquals(403, addCategoryResponse.getStatusCode());
    }

    @Test
    public void whenUserIsLoggedAsAdminUpdateSubCategoryShouldReturn200() {
        // @formatter:off
        addCategory("Blenders");
        var addSubCategoryResponse = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"categoryName\" : \"Blenders\",\n" +
                        "    \"subCategoryName\" : \"Philips\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addSubCategory/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        var updateSubCategoryResponse = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"subCategoryOldName\" : \"Philips\",\n" +
                        "    \"subCategoryNewName\" : \"Sony\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateSubCategory/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        assertEquals(200, updateSubCategoryResponse.getStatusCode());
    }

    @Test
    public void whenUserIsNotAdminUpdateCategoryShouldReturn403() {
        // @formatter:off
        var loginResponse = given().log().all()
                .contentType("application/json")
                .body(new LoginRequest("jaz", "jaz"))
                .when()
                .post("http://localhost:" + port + "/api/login/")
                .thenReturn();
        var updateCategoryResponse = given().log().all()
                .cookies(loginResponse.getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "    \"oldCategoryName\" : \"TV\",\n" +
                        "    \"newCategoryName\" : \"Kate\"\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addCategory/")
                .then()
                .log().all()
                .statusCode(403)
                .extract().response();

        // @formatter:on
        assertEquals(403, updateCategoryResponse.getStatusCode());
    }

    @Test
    public void ListOfAllSubCategoriesIsAvailableForEveryBodyShouldReturn200() {
        // @formatter:off
        addCategory("Bus");
        addSubCategory("Bus", "BMW");
        var response = given().log().all()
                .contentType("application/json")
                .when()
                .get("http://localhost:" + port + "/api/listSubCategories/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        Assert.assertTrue(response.asString().contains("BMW"));
    }
}
