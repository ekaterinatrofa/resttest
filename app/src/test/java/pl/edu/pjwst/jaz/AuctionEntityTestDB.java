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

public class AuctionEntityTestDB {
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
    public void whenUserIsLoggedAndAddAuctionShouldReturn200() {
        // @formatter:off
        addCategory("CellPhones");
        addSubCategory("CellPhones", "Sony");
        var addAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Xperia\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"Sony\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                                "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        Assert.assertTrue(addAuctionResponse.asString().contains("Xperia"));
        assertEquals(200, addAuctionResponse.getStatusCode());
    }

    @Test
    public void whenUserIsNotLoggedInAddAuctionShouldReturn400() {
        // @formatter:off
        addCategory("Phones");
        addSubCategory("Phones", "Apple");
        var addAuctionResponse = given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Iphone11\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"Apple\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .thenReturn();
        // @formatter:on
        assertEquals(400, addAuctionResponse.getStatusCode());
    }

    @Test
    public void whenUserIsLoggedInUpdateAuctionShouldReturn200() {
        // @formatter:off
        addCategory("Blenders");
        addSubCategory("Blenders","Philips");
        var addAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Iphone11\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"Philips\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .thenReturn();
        var updateAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"oldAuctionTitle\" : \"Iphone11\",\n" +
                        "\"oldAuctionPrice\" : 500,\n" +
                        "\"newAuctionTitle\" : \"PhilipsKT\",\n" +
                        "\"newAuctionDescription\" : \"Kettle\",\n" +
                        "\"subCategoryName\" : \"Philips\",\n" +
                        "\"newAuctionPrice\" : 100,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"]\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateAuction/")
                .thenReturn();
        // @formatter:on
        assertEquals(200, updateAuctionResponse.getStatusCode());
    }

    @Test
    public void whenUserIsNotAbleToModifyAuctionBelongsToOtherUserShouldReturn403() {
        // @formatter:off
        addCategory("Phones");
        addSubCategory("Phones", "Apple");
        var addAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Iphone11\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"Apple\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .thenReturn();
        var updateAuctionResponse = given().log().all()
                .cookies(login("Admin", "admin").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"oldAuctionTitle\" : \"Iphone11\",\n" +
                        "\"oldAuctionPrice\" : 500,\n" +
                        "\"newAuctionTitle\" : \"PhilipsKT\",\n" +
                        "\"newAuctionDescription\" : \"Kettle\",\n" +
                        "\"subCategoryName\" : \"Apple\",\n" +
                        "\"newAuctionPrice\" : 100,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"]\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateAuction/")
                .then()
                .log().all()
                .extract().response();

        // @formatter:on
        assertEquals(403, updateAuctionResponse.getStatusCode());

    }

    @Test
    public void optimisticLock() {
        // @formatter:off
        addCategory("Blenders");
        addSubCategory("Blenders","Philips");
        var addAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Iphone11\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"Philips\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .thenReturn();
        var updateAuctionResponse1 = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"oldAuctionTitle\" : \"Iphone11\",\n" +
                        "\"oldAuctionPrice\" : 500,\n" +
                        "\"newAuctionTitle\" : \"PhilipsKT\",\n" +
                        "\"newAuctionDescription\" : \"Kettle\",\n" +
                        "\"subCategoryName\" : \"Philips\",\n" +
                        "\"newAuctionPrice\" : 100,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"]\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateAuction/")
                .thenReturn();
        var updateAuctionResponse2 = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"oldAuctionTitle\" : \"Iphone11\",\n" +
                        "\"oldAuctionPrice\" : 500,\n" +
                        "\"newAuctionTitle\" : \"PhilipsKT\",\n" +
                        "\"newAuctionDescription\" : \"Kettle\",\n" +
                        "\"subCategoryName\" : \"Philips\",\n" +
                        "\"newAuctionPrice\" : 100,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"]\n" +
                        "}")
                .post("http://localhost:" + port + "/api/updateAuction/")
                .thenReturn();
        // @formatter:on
        assertEquals(403, updateAuctionResponse2.getStatusCode());

    }

    @Test
    public void ListOfAllAuctionsIsAvailableForEveryBodyShouldReturn200() {
        // @formatter:off
        addCategory("Bus");
        addSubCategory("Bus", "BMW");
        var addAuctionResponse = given().log().all()
                .cookies(login("jaz", "jaz").getCookies())
                .contentType("application/json")
                .body("{\n" +
                        "\"auctionTitle\" : \"Iphone11\",\n" +
                        "\"auctionDescription\" : \"used phone\",\n" +
                        "\"subCategoryName\" : \"BMW\",\n" +
                        "\"auctionPrice\" : 500,\n" +
                        "\"photos\" : " +
                        "[\"https://www.mediaexpert.pl/media/cache/gallery/product/0/636/403/663/7grkxvcj/images/26/2652093/Smartfon-APPLE-iPhone-11--Black-Front-Tyl.jpg\"],\n" +
                        "\"parameters\" : {\"memory_size\" : \"64GB\",\"screen\" : \"6.1\"}\n" +
                        "}")
                .post("http://localhost:" + port + "/api/addAuction/")
                .thenReturn();
        var response = given().log().all()
                .contentType("application/json")
                .when()
                .get("http://localhost:" + port + "/api/listAuctions/")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        // @formatter:on
        Assert.assertTrue(response.asString().contains("Iphone11"));
    }
}
