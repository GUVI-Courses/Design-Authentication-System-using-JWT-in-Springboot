package com.jwt.auth.jwt_auth.controllers;

import com.jwt.auth.jwt_auth.models.TokenRequest;
import com.jwt.auth.jwt_auth.models.TokenResponse;
import com.jwt.auth.jwt_auth.security.JwtAuthHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest request){
        this.doAuthenticate(request.getEmail(),request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        TokenResponse response = TokenResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        try {
            authenticationManager.authenticate(authentication);
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username and Password");

        }

    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Invalid Credentials";
    }

}
