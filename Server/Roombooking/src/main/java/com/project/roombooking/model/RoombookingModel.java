package com.project.roombooking.model;

import java.sql.Date;

public class RoombookingModel {
	
	private String sNo;
	private String roomNo;
	private String price;	
	private String  numberofRooms;
	private String  durationtime;
	private String  roomImage;		
	public String getsNo() {
		return sNo;
	}
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNumberofRooms() {
		return numberofRooms;
	}
	public void setNumberofRooms(String numberofRooms) {
		this.numberofRooms = numberofRooms;
	}
	public String getDurationtime() {
		return durationtime;
	}
	public void setDurationtime(String durationtime) {
		this.durationtime = durationtime;
	}
	public String getRoomImage() {
		return roomImage;
	}
	public void setRoomImage(String roomImage) {
		this.roomImage = roomImage;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;

}
