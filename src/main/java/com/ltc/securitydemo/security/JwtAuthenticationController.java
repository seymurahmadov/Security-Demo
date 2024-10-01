package com.ltc.securitydemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final UserDetailsService jwtInMemoryUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final UserRepo userRepo;

	private final UserService userService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService jwtInMemoryUserDetailsService, PasswordEncoder passwordEncoder, UserRepo userRepo, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping("/signing")
	public String  signIn(@RequestBody JwtRequest request)
			throws Exception {

		authenticate(request.getEmail(), request.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(request.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AfterSignInResponseDto signInResponseDto = AfterSignInResponseDto.builder()
				.token(token)
				.email(request.getEmail())
				.password(request.getPassword())
				.role(request.getRole().name())
				.build();

		return  signInResponseDto.toString();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp (@RequestBody SignUpDto dto){

		UserEntity entity = userRepo.findUsersEntityByEmail(dto.getEmail());
		if (entity == null) {
			UserEntity userEntity = UserEntity.builder()
					.email(dto.getEmail())
					.password(passwordEncoder.encode(dto.getPassword()))
					.role(dto.getRole())
					.build();
			userRepo.save(userEntity);
			return ResponseEntity.ok("You signed!");
		}else
			return ResponseEntity.ok("This account already exist in our DB!");
	}



	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


	@PutMapping("/update{id}")
	public String update(@RequestBody JwtRequest request, @PathVariable Long id){
		return userService.update(id, request);
	}



	@GetMapping("/get-all")
	public List<JwtRequest> getAll(){
		return userService.getAll();
	}



}
