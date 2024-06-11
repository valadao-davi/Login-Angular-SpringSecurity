package com.apirest.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.userdept.entities.User;
import com.apirest.userdept.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public List<User> findAll(){
		List<User> result = repository.findAll();
		return result;
	}
	@GetMapping(value = "/{id}")
	public User findOne(@PathVariable Long id){
		User result = repository.findById(id).get();
		return result;
	}
	@PostMapping
	public User createUser(@RequestBody User user){
		User result = repository.save(user);
		return result;
	}
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
	@PutMapping(value = "/{id}")
	public User editUser(@PathVariable Long id, @RequestBody User user) {
		User userAntigo = repository.findById(id).get();
		userAntigo.setName(user.getName());
		userAntigo.setEmail(user.getEmail());
		userAntigo.getDepartment().setId(user.getDepartment().getId());
		userAntigo.setEmail(user.getEmail());
		User userNovo = repository.save(userAntigo);
		return userNovo;
	}
}
