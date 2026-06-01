package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class parsingXMLFile {

    @Test
    public void parsingXmlValidation(){
        //https://www.w3schools.com/xml/simple.xml
        given().when()
                .get("https://www.w3schools.com/xml/simple.xml")
                .then().statusCode(200)
                .header("Content-Type","text/xml")
                .body("breakfast_menu.food.name[0]",equalTo("Belgian Waffles")) ;
    }

    @Test
    public  void approach2(){
        Response res =  given().when()
                .get("https://www.w3schools.com/xml/simple.xml");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"text/xml");
       String firstFoodname =  res.xmlPath().get("breakfast_menu.food.name[0]").toString();
       Assert.assertEquals(firstFoodname ,"Belgian Waffles");

        XmlPath path = new XmlPath(res.asString());

        List<String> foodNames =   path.getList("breakfast_menu.food.name");
        for (String foodName : foodNames){
            System.out.println(foodName);
        }

        //List<String> foodNames = res.xmlPath().get()
    }
}
