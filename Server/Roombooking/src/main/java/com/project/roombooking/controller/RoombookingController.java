package com.project.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roombooking.dao.RoombookingDao;
import com.project.roombooking.model.Response;
import com.project.roombooking.model.RoombookingModel;



@CrossOrigin("*")
@RestController
@RequestMapping("/roomdetails")
public class RoombookingController {
	@Autowired
	private RoombookingDao dao;

	@PostMapping("/createdetails/{s_no}")
	public ResponseEntity<Response> createdetails (@PathVariable String s_no, @RequestBody RoombookingModel datas) {
		return ResponseEntity.ok(dao.createdetails( s_no ,datas));
	}

	@PutMapping("/editroomdetails")
	public ResponseEntity<Response> editroomdetails(@RequestBody RoombookingModel datas) {
		return ResponseEntity.ok(dao.editroomdetails(datas));

	}

	@PostMapping("/getAllroom")
	public ResponseEntity<Response> getAllroom() {
		return ResponseEntity.ok(dao.getAllroom());

	}

	@GetMapping("/getOneroom/{s_no}")
	public ResponseEntity<Response> getOneroom(@PathVariable String s_no) {
		return ResponseEntity.ok(dao.getOneroom(s_no));

	}

	@GetMapping("/getOneownerroom/{s_no}")
	public ResponseEntity<Response> getOneownerroom(@PathVariable String s_no) {
		return ResponseEntity.ok(dao.getOneownerroom(s_no));

	}
	

}
