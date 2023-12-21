package com.project.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.roombooking.dao.BookingDao;
import com.project.roombooking.model.BookingModel;
import com.project.roombooking.model.Response;
import com.project.roombooking.model.RoombookingModel;


@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {
	
		@Autowired
		private BookingDao dao;

		@PostMapping("/createbooking/{s_no}")
		public ResponseEntity<Response> insertbooking(@PathVariable String s_no,@RequestBody BookingModel datas ) {
			return ResponseEntity.ok(dao.insertbooking(s_no, datas));
		}

		@PutMapping("/editbooking/{s_no}")
		public ResponseEntity<Response> editbooking(@RequestBody BookingModel datas) {
			return ResponseEntity.ok(dao.editbooking(datas));

		}
		@GetMapping("/getonebooking/{s_no}")
		public ResponseEntity<Response> getonebooking(@PathVariable String s_no) {
			return ResponseEntity.ok(dao.getonebooking(s_no));
		}

		@DeleteMapping("/deletebooking/{s_no}")
		public ResponseEntity<Response> delete(@PathVariable String s_no) {
			return ResponseEntity.ok(dao.delete(s_no));
		}


}
