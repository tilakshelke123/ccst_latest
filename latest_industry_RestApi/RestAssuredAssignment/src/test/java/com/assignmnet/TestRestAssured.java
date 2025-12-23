package com.assignmnet;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;







public class TestRestAssured {

	
	
	@Test
	static void TestSomething() {
		RestAssured.baseURI ="https://restcountries.com";
		Response resp = RestAssured.get("/v3.1/lang/spanish");
		
		
		 System.out.println("resposne body :"+resp.getStatusCode());
		 
		 
		 System.out.println("resposne body :");
		 System.out.println(resp.getBody().asString());
		
		 JsonPath jsonPath =resp.jsonPath();
			List<Map<String,Object>> languageList = jsonPath.getList("languages");
		
			for(Map<String,Object> sub :languageList) {
				if(sub != null) {
					System.out.println(sub.keySet());
				}
				}
			
			Assert.assertEquals(+resp.getStatusCode(), 200,"Test Cases passed");
			Assert.assertTrue(resp.getBody().asString().contains("languages"),"Request passed");
//	Assert.assertFalse(resp.getBody().asString().contains("languages"),"Request passed");
	}
}
