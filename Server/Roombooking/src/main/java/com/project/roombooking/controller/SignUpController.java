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

import com.project.roombooking.dao.SignUpDao;
import com.project.roombooking.model.Response;
import com.project.roombooking.model.SignUpModel;
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignUpController {
	@Autowired(required=true)
	   private  SignUpDao dao;

		@PostMapping("/create")
		public ResponseEntity<Response> createUser(@RequestBody SignUpModel datas) {
			return ResponseEntity.ok(dao.createUser(datas));

		}

		@PutMapping("/edit")
		public ResponseEntity<Response> editDetails(@RequestBody SignUpModel datas) {
			return ResponseEntity.ok(dao.editDedails(datas));

		}

		@DeleteMapping("delete")
		public ResponseEntity<Response> deleteDetails(@RequestParam String sNo) {
			return ResponseEntity.ok(dao.deleteDetails(sNo));

		}

		@PostMapping("/loginpage")
		public ResponseEntity<Response> login(@RequestBody SignUpModel datas) {
			return ResponseEntity.ok(dao.login(datas));

		}

		@GetMapping("/getonedata/{s_no}")
		public ResponseEntity<Response> getoneData(@PathVariable String s_no) {
			return ResponseEntity.ok(dao.getoneData(s_no));

		}
		
		@GetMapping("/getall")
		public ResponseEntity<Response> getall() {
			return ResponseEntity.ok(dao.getall());

		}


}
