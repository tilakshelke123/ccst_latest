package microservice.copy;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;


public class PostTest {

	@Test
	public static void test_post() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/posts";
		
		Map<String, Object> weatherData= new HashMap<String, Object>();
		
		weatherData.put("city", "New York");
		weatherData.put("temptrature", 28);
		weatherData.put("humidity", 60);
		weatherData.put("condition", "Sunny");
		
		RequestSpecification obj_request=RestAssured
				.with()
				.contentType(ContentType.JSON)
				.body(weatherData);
		
		//Send post request
		Response rep= obj_request.post();
		
		System.out.println("Status_code:"+rep.getStatusCode());
		System.out.println("Responce body: "+rep.getBody().asString());
		
		Assert.assertEquals(rep.getStatusCode(), 201,"Request passed");
		
//		if(rep.getStatusCode()==201) {
//			System.out.println("succesfully post");
//		}
//		else {
//			System.out.println("unsuccefull..");
//		}
	
	}

}
