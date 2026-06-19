package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import api.BookingAPI;
import base.BaseTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.common.BookingDates;
import models.request.CreateBookingRequest;
import models.request.UpdateBookingRequest;
import models.response.BookingDetailsResponse;
import utils.JSONReader;
import utils.SchemaValidator;

public class BookingTest extends BaseTest {
	
	
	BookingAPI book = new BookingAPI();
	
	BookingDates bookingdates = new BookingDates();

	
	static int bookingid;
	
	
	
	@Test(priority=1)
	
	 @Description("Create Booking Positive Test")
	public void createBookingPositive() {
		
		JsonNode data = JSONReader.getTestData("createBookingPositive");
		
		CreateBookingRequest request = new CreateBookingRequest();
		
		request.setFirstname( data.get("firstname").asText());
		
		request.setLastname(data.get("lastname").asText());
		
		request.setTotalprice(data.get("totalprice").asInt());
		
		
		request.setDepositpaid(data.get("depositpaid").asBoolean());
		
		
		JsonNode bookingDatesNode =
		        data.get("bookingdates");

		bookingdates.setCheckin(
		        bookingDatesNode.get("checkin").asText());

		bookingdates.setCheckout(
		        bookingDatesNode.get("checkout").asText());

		request.setBookingdates(bookingdates);
		
		request.setAdditionalneeds(data.get("additionalneeds").asText());
		
		Response response = book.createBooking(request);
		
		
		
		response.then()
		.statusCode(200);
		SchemaValidator.validateSchema(
		        response,
		        "createBookingSchema.json");
		
		BookingDetailsResponse res = response.as(BookingDetailsResponse.class);
		
		bookingid = res.getBookingid();
		
		
		System.out.println(
                "Created Booking ID : "
                        + bookingid);
		Assert.assertEquals(
		        res.getBooking().getFirstname(),
		        request.getFirstname());
		
		
	}	
	
	
	
	@Test(priority=2)
	@Description("getBookingPositive")
	public void getBookingPositive() {
		
		
		Response response = book.getBooking(bookingid);
		
		
	    response.then().statusCode(200);
	    
		
	}
	
	@Test(priority=3)
	@Description("getBookingNegative")
	public void getBookingNegative() {
		
		Response response = book.getBooking(111111);
		
		
	    response.then().statusCode(404);
	    
	  
		

	}
	
	
	@Test(priority=4)
	public void updateBooking() {
		
		
		JsonNode data = JSONReader.getTestData("updateBookingPositive");
		
		UpdateBookingRequest request = new UpdateBookingRequest();
		
		
		request.setFirstname(data.get("firstname").asText());
		
		request.setLastname(data.get("lastname").asText());
		
		String updatedfirstName=data.get("firstname").asText();
		
		request.setTotalprice(data.get("totalprice").asInt());
		
		
		request.setDepositpaid(data.get("depositpaid").asBoolean());
		
		
		JsonNode bookingDatesNode =
		        data.get("bookingdates");

		bookingdates.setCheckin(
		        bookingDatesNode.get("checkin").asText());

		bookingdates.setCheckout(
		        bookingDatesNode.get("checkout").asText());

		request.setBookingdates(bookingdates);
		
		request.setAdditionalneeds(data.get("additionalneeds").asText());
		
		
		Response response =
				book.updateBooking(
				        bookingid,
				       request, token);
		

		
		
	    response.then().statusCode(200);
	    
	    response.prettyPrint();
	    

	    Response getResponse = book.getBooking(bookingid);

	    CreateBookingRequest res =	getResponse.as(CreateBookingRequest.class);

	    Assert.assertEquals(
	    					res.getFirstname(),
	    					updatedfirstName);
	    
		    
	    System.out.println("Updated User name:"+res.getFirstname());

	}
	
	
	
	@Test(priority=5)
	public void deleteBookingPositive() {
		
	      Response response =
	                book.deleteBooking(bookingid, token);

	        response.then().statusCode(201);
	        
	        response.prettyPrint();

	        System.out.println("Deleted User ID : "
	                        + bookingid);

	}
	
	@Test(priority=6)
	public void deleteBookingNegative() {
		
		Response response =
                book.deleteBooking(111111, token);

        response.then().statusCode(405);
        
        response.prettyPrint();

        System.out.println("Booking id not exists");
		
	}
	

}
