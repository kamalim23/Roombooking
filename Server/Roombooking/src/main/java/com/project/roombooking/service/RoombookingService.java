package com.project.roombooking.service;

import org.springframework.stereotype.Service;


import com.project.roombooking.model.Response;
import com.project.roombooking.model.RoombookingModel;
@Service
public interface RoombookingService {
	public Response createdetails( String sNo,RoombookingModel datas);
	public Response editroomdetails(RoombookingModel datas);
	public Response getAllroom() ;
	public Response getOneroom(String sNo);
	public Response getOneownerroom(String s_no);

		

}
