package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value= "/users")
public class UserResources {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
		UserDTO dto = service.findUserById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/profile")
	public ResponseEntity<UserDTO> findSelfProfile(){
		UserDTO dto = service.findSelfUser();
		return ResponseEntity.ok().body(dto);
	}
}
