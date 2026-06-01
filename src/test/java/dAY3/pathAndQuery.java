package dAY3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class pathAndQuery {

    //https://www.google.com/?zx=1780237683711
    @Test
    void pathAndQuery(){

        given().queryParam("zx","1780237683711")
                .when().get("https://www.google.com/")
                .then().statusCode(200).log().all();
    }
}
