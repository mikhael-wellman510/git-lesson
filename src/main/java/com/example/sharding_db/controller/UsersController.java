package com.example.sharding_db.controller;

import com.example.sharding_db.service.UsersServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

	private final UsersServiceImpl usersService;

	public UsersController(UsersServiceImpl usersService){
		this.usersService = usersService;
	}

	@GetMapping("/find")
	public ResponseEntity<?>findByDb(@RequestParam(name = "id")Long id){
		var data = usersService.findUser(id);
		return ResponseEntity.ok(data);
	}
}
