package services;

import body.PostBookBody;
import body.PostTokenBody;
import body.UpdateBookBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import static org.hamcrest.Matchers.*;

public class ApiTesting {

    static String baseURI="https://restful-booker.herokuapp.com";
    static String tokenId;
    static String bookingId;



    @Test (priority = 6)
    public void sample(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/");

        System.out.println("getBody:" + response.asString());
        System.out.println("getBody:" + response.getBody().asString());
        System.out.println("getStatusCode:" + response.getStatusCode());
        System.out.println("getContentType:" + response.getHeader("Content-Type"));
        System.out.println("getTime" + response.getTime());

        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test (priority = 3)
    public static void createToken(){

        Response response = given()
                .header("Content-Type", "application/json")
                .baseUri(baseURI)
                .log()
                .all()
                .body(PostTokenBody.tokenBody())
                .when()
                .post("/auth");

        tokenId = response.jsonPath().getString("token");
    }

    @Test (priority = 2)
    public static void postBook() {
        Response response = given()
                .header("Content-Type", "application/json")
                .baseUri(baseURI)
                .log()
                .all()
                .body(PostBookBody.bodyInfo())
                .when()
                .post("/booking");

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);

    }
    @Test (priority = 1)
    public void getBook(){
        String response=given()
                .header("Content-Type","application/json")
                .baseUri(baseURI)
                .log()
                .all()
                .when()
                .get("/booking")
                .then()
                .extract().response().asString();

        System.out.println(response);
    }


    @Test (priority = 4)
    public static void UpdateBook(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie","token="+tokenId+"")
                .baseUri(baseURI)
                .log()
                .all()
                .body(UpdateBookBody.updateBody())
                .when()
                .put("/booking/"+bookingId);

    }
    @Test (priority = 5)
    public static void DeleteBook(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie","token="+tokenId+"")
                .baseUri(baseURI)
                .log()
                .all()
                .body(PostTokenBody.tokenBody())
                .when()
                .delete("/booking/"+bookingId);
    }

}
