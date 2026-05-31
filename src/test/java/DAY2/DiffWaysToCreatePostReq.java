package DAY2;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DiffWaysToCreatePostReq {


    @Test
    void usingHashMap() {
        HashMap map = new HashMap();
        map.put("name", "Aswin");
        map.put("location", "Chennai");
        String courseArr[] = {"Java", "Selenium", "API Testing"};
        map.put("courses", courseArr);


        given().contentType("application/Json").body(map)

                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("name", equalTo("Aswin"))
                .header("Content-Type","application/Json; charset=utf-8")
                .log().all();
    }
@Test
    void delete(){
        given()
                .when().delete("http://localhost:3000/students/2V0eP6jmjLQ").then().statusCode(200);
    }

    @Test
    void usingJsonObject() {
        JSONObject map = new JSONObject();
        map.put("name", "Aswin");
        map.put("location", "Chennai");
        String courseArr[] = {"Java", "Selenium", "API Testing"};
        map.put("courses", courseArr);


        given().contentType("application/Json").body(map.toString())

                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("name", equalTo("Aswin"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test
    void usingPojoClass() {
pojoClass data = new pojoClass();
data.setName("Albert");
data.setLocation("Chennai");
        String courseArr[] = {"Java", "Selenium", "API Testing"};
        data.setCourses(courseArr);


        given().contentType("application/Json").body(data)

                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("name", equalTo("Albert"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test
    void usingExternalJson() throws FileNotFoundException {
       File file = new File(".\\data.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
JSONObject data = new JSONObject(jsonTokener);

        given().contentType("application/Json").body(data.toString())

                .when().post("http://localhost:3000/students")
                .then().statusCode(201).body("name", equalTo("Aswin"))
                .header("Content-Type", "application/json")
                .log().all();
    }
}
