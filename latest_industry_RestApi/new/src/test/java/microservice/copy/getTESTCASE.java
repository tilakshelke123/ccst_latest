package microservice.copy;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.*;
import org.testng.annotations.Test;

public class getTESTCASE {
	@Test
	public void ccst_GETTEST() {
		
		RestAssured.baseURI = "https://restcountries.com/";//baseurl
		Response resp = RestAssured.get("/v3.1/name/all");
		// getting status  code 
		 System.out.println("status code:"+resp.getStatusCode());//endpoint
		 
		 
		 //print the response body 
		 System.out.println("resposne body :");
		 System.out.println(resp.getBody().asString());
		 
		 //extract curries
		 
		JsonPath jsonPath =resp.jsonPath();
		List<Map<String,Object>> currencyList = jsonPath.getList("currencies");
		
		for(Map<String,Object> curreuncy :currencyList) {
		if(curreuncy != null) {
			System.out.println(curreuncy.keySet());
		}
		}
		
		//validations for status code
		Assert.assertEquals(+resp.getStatusCode(), 200,"Test Cases passed");
		Assert.assertTrue(resp.getBody().asString().contains("name"),"Requessed passed");
		
//		if(resp.getStatusCode()==200) {
//			System.out.println("Test Passed !!!!"+resp.getStatusCode());
//		}else {
//			System.out.println("Test Failed !!!!"+resp.getStatusCode());
//		}
	}

}
