package com.project.roombooking.model;

import java.sql.Date;

public class BookingModel {
	
	
	private String roomNo;
	private String name;
	private long adharNumner;
	private long mobileNumber;
	private String bookingTo;
	private String bookingBy;
	private Date checkIn;
	private Date checkOut;
	private String status;
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAdharNumner() {
		return adharNumner;
	}
	public void setAdharNumner(long adharNumner) {
		this.adharNumner = adharNumner;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBookingTo() {
		return bookingTo;
	}
	public void setBookingTo(String bookingTo) {
		this.bookingTo = bookingTo;
	}
	public String getBookingBy() {
		return bookingBy;
	}
	public void setBookingBy(String bookingBy) {
		this.bookingBy = bookingBy;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
