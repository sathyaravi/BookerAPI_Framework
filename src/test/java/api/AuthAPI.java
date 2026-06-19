package api;

import static base.BaseTest.getRequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import models.request.AuthRequest;

public class AuthAPI {
	
public static Response createToken(AuthRequest authRequest) {
		
		return given()
				
			.spec(getRequestSpec())
			
			.body(authRequest)
				
			.when()
			
			.post("/auth");
	}

}
