package api;

import io.restassured.response.Response;
import models.request.CreateBookingRequest;
import models.request.UpdateBookingRequest;

import static io.restassured.RestAssured.given;

import base.BaseTest;

public class BookingAPI {
	
        public Response createBooking(CreateBookingRequest requestBody) {
				
        	
        		return given()
        				
        		.spec(BaseTest.getRequestSpec())
        		
        		.body(requestBody)
        		
        		.log().all()
        		
        		.when()
        		
        		.post("/booking")
        		
        		.then()
        		
        		.extract().response();
        	
        
        
        }
        
        
        public Response getBooking(int bookingid) {
        	
        	
        	return given()
        			
        	.spec(BaseTest.getRequestSpec())
        	
        	.when()
        	
        	.log().all()
        	
        	.get("/booking/"+bookingid)
        	
        	
        	
        	.then()
        	
        	.extract().response();
			

		}
        
        
        
        public Response updateBooking(int bookingid,UpdateBookingRequest requestBody,String token) {
        	
        	return given()
        			
        	.spec(BaseTest.getRequestSpec())
        	
        	.header("Cookie","token=" + token)
        	
        	.body(requestBody) 
        	
        	.log().all()
        	
        	.when()
        	.put("/booking/"+bookingid)
        	
        	.then()
        	
        	.extract().response();
			
		}
        
        
        public Response deleteBooking(int bookingid,String token) {
        	
        	
        	return given()
        			
        	.spec(BaseTest.getRequestSpec())
        	
        	.header("Cookie","token=" + token)
        	
        	.log().all()
        	
        	.when()
        	
        	.delete("/booking/"+bookingid)
        	
        	.then()
        	
        	.extract().response();
			

		}
}
