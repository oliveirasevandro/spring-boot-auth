package com.auth0.samples.authapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private final ApplicationUserRepository applicationUserRepository;

	private final BCryptPasswordEncoder encoder;

	@Autowired
	public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder encoder) {

		this.applicationUserRepository = applicationUserRepository;
		this.encoder = encoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {

		user.setPassword(encoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
	}
}
