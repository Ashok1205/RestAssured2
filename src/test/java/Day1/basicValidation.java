package Day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class basicValidation {

int userId ;
    @Test(enabled = false)
void getUser(){

    given()
            .when().get("https://jsonplaceholder.typicode.com/posts/1")
            .then().statusCode(200)
            .body("id",equalTo(1))
            .log().all();
}

@Test (priority = 1)
void createUser() {
    HashMap map = new HashMap();
    map.put("userId","21");
    map.put("id","1");
    map.put("title","repellat provident occaecati excepturi optio reprehenderit");
    userId =  given().contentType("application/json").body(map)
                .when().post("https://jsonplaceholder.typicode.com/posts")
            .jsonPath().getInt("userId");
                //.then().statusCode(201).log().all();
    }

    @Test (priority = 2)
    void updateUser() {
        HashMap map = new HashMap();
        map.put("userId","21");
        map.put("id","1");
        map.put("title","put Validation");
        given().contentType("application/json").body(map)
                .when().put("https://jsonplaceholder.typicode.com/posts/1" )
                .then().statusCode(200).log().all();
    }

}
