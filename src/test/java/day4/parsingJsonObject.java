package day4;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class parsingJsonObject {


    public  void usingJsonFinder(){
        given()
                .when().get("http://localhost:3000/students") .then().statusCode(200)
               .body("[6].name" ,equalTo("Aswin"));

//       String contentType = res.getHeader("Content-Type");
//        System.out.println(contentType);

    }
    @Test(priority = 1)
    public  void usingAssertion(){
      Response res =   given()
                .when().get("http://localhost:3000/students") ;

        Assert.assertEquals(res.getStatusCode(),200);

        //To get all the names from the Json

        String response = res.getBody().asString() ;

        JSONArray jsonArray = new JSONArray(response);
        for(int i = 0 ; i<jsonArray.length();i++){
            String names = jsonArray.getJSONObject(i).getString("name");
        }

    }


    @Test(priority = 1)
    public void usingAssertion1() {

        Response res = given()
                .when()
                .get("http://localhost:3000/students");

      //  Assert.assertEquals(res.getStatusCode(), 200);

       String responseBody = res.getBody().asString();

       JSONArray jsonArray = new JSONArray(responseBody);

       for(int i =0 ; i<jsonArray.length();i++){
           String names = jsonArray.getJSONObject(i).get("name").toString();
           //   String name = jsonArray.getJSONObject(i).getString("name");
              System.out.println(names);
        }
    }

}
