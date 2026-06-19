package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import models.common.BookingDates;
import models.request.CreateBookingRequest;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BookingDetailsResponse {

	private String firstname;
	private String lastname;
	
	private int totalprice;
	
	private boolean depositpaid;
	
	private BookingDates bookingdates;
	
	private CreateBookingRequest booking; 

	
	private int bookingid;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}


	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public CreateBookingRequest getBooking() {
		return booking;
	}

	public void setBooking(CreateBookingRequest booking) {
		this.booking = booking;
	}
	
	
}
