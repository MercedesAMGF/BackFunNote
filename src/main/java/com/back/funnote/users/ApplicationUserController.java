package com.back.funnote.users;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

	private ApplicationUserService applicationUserService;
	private ApplicationUserRepository applicationUserRepository;

	public ApplicationUserController(ApplicationUserService applicationUserService, ApplicationUserRepository applicationUserRepository) {
		this.applicationUserService = applicationUserService;
		this.applicationUserRepository = applicationUserRepository;
	}

	@GetMapping
	public List<ApplicationUser> getAllUsers() {
		return this.applicationUserService.getAllApplicationUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApplicationUser> getUserById(@PathVariable("id") String id) {

		ApplicationUser user = applicationUserRepository.findById(id).get();
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@PostMapping
	public ApplicationUser postUser(@Valid @RequestBody RegisterUser user) {
		return this.applicationUserService.saveApplicationUser(user);
	}

	@PostMapping("/sign-up")
	public ApplicationUser signUp(@RequestBody RegisterUser registerUser) {
		return this.applicationUserService.saveApplicationUser(registerUser);
	}

	@PutMapping
	public ResponseEntity<ApplicationUser> updateUser(@Valid @RequestBody RegisterUser user) {
		ApplicationUser updatedUser = this.applicationUserService.updateApplicationUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") String id) {
		this.applicationUserRepository.deleteById(id);
	}

}
