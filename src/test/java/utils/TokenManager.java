package utils;

import api.AuthAPI;
import io.restassured.response.Response;
import models.request.AuthRequest;
import models.response.AuthResponse;

public class TokenManager {

	
	 private static String token;

	    public static String getToken() {

	        if(token == null) {

	            AuthRequest request =
	                    new AuthRequest();

	            request.setUsername("admin");
	            request.setPassword("password123");

	            new AuthAPI();
				Response response =
	                    AuthAPI
	                    .createToken(request);

	            token =
	              response.as(AuthResponse.class)
	                      .getToken();
	        }

	        return token;
	    }
}
