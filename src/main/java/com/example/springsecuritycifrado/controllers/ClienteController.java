package com.example.springsecuritycifrado.controllers;

import com.example.springsecuritycifrado.entities.User;
import com.example.springsecuritycifrado.repositories.UserRepository;
import com.example.springsecuritycifrado.security.jwt.JwtTokenUtil;
import com.example.springsecuritycifrado.security.payload.JwtResponse;
import com.example.springsecuritycifrado.security.payload.LoginRequest;
import com.example.springsecuritycifrado.security.payload.MessageResponse;
import com.example.springsecuritycifrado.security.payload.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta clase realiza el control de la autenticacion de usuarios.
 * En caso de que el usuario este autenticado correctamente se
 * genera u token JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class ClienteController {

    private final UserRepository authRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil tokenUtil;

    public ClienteController(UserRepository authRepository, AuthenticationManager authManager, PasswordEncoder passwordEncoder, JwtTokenUtil tokenUtil) {
        this.authRepository = authRepository;
        this.authManager = authManager;
        this.encoder = passwordEncoder;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenUtil.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest){
        if(authRepository.existsByUsername(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Username is registered, please insert an original username."));
        }
        if(authRepository.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email es registered, please insert an unique email account"));
        }

        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));

        authRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully."));
    }
}
