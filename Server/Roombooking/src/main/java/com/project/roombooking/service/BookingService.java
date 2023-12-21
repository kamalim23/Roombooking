package com.project.roombooking.service;

import org.springframework.stereotype.Service;

import com.project.roombooking.model.BookingModel;
import com.project.roombooking.model.Response;
@Service
public interface BookingService {
	public Response insertbooking(String s_no, BookingModel datas);
	public Response editbooking(BookingModel datas);
	public Response getonebooking(String s_no);
	public Response delete(String sNo);


}
