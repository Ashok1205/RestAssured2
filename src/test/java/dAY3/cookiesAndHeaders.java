package dAY3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;

public class cookiesAndHeaders {

    @Test
    void validateCookies(){
      Response res =  given().queryParam("zx","1780237683711")
                .when().get("https://www.google.com/") ;

      String SingleCookie = res.getCookie("AEC");

     Map<String , String > allCookies = res.getCookies() ;
        System.out.println(allCookies.keySet());
        for(String k : allCookies.keySet()){
            System.out.println(k + "-->" + res.getCookie(k));
        }

    }

    @Test
    void validateHeader(){
        Response res =  given().queryParam("zx","1780237683711")
                .when().get("https://www.google.com/") ;
String singleheader = res.getHeader("Content-Type") ;
        System.out.println(singleheader);


        //to print all headers
        Headers headers = res.getHeaders();

        for (Header hd : headers){
            System.out.println(hd.getName() +  "    --->" + hd.getValue());

            //for validating use then().header("Content-Type") ;
        }
    }
}
