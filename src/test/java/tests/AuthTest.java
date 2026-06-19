package tests;

import io.restassured.response.Response;
import models.request.AuthRequest;
import models.response.AuthResponse;

import org.testng.annotations.Test;

import api.AuthAPI;
import base.BaseTest;

public class AuthTest extends BaseTest {
	
	
	
	@Test
	public void authTest() {
		
		AuthRequest authRequest = new AuthRequest();
		
		authRequest.setUsername("admin");
		
		
		authRequest.setPassword("password123");
		
		Response response = AuthAPI.createToken(authRequest);
		
		response.then().statusCode(200);
		
		
		AuthResponse authResponse = response.as(AuthResponse.class);
		
		token = authResponse.getToken();
		
		

	}
	

}
