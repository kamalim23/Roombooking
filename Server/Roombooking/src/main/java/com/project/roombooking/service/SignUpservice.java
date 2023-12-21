package com.project.roombooking.service;

import org.springframework.stereotype.Service;

import com.project.roombooking.model.Response;
import com.project.roombooking.model.SignUpModel;
@Service
public interface SignUpservice {
	public Response createUser(SignUpModel datas);
	public Response login(SignUpModel datas);
	public Response editDedails(SignUpModel datas);
	public Response deleteDetails(String sNo) ;
	public Response getall();

}
