package models.response;

import models.request.CreateBookingRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateBookingResponse {
	

    private int bookingid;

    private CreateBookingRequest booking;

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public CreateBookingRequest getBooking() {
		return booking;
	}

	public void setBooking(CreateBookingRequest booking) {
		this.booking = booking;
	}

}
