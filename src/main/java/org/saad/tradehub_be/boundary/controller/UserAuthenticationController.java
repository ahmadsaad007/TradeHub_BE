package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.LoginRequest;
import org.saad.tradehub_be.boundary.request.SignUpRequest;
import org.saad.tradehub_be.services.AuthControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private AuthControlService authController;

    @GetMapping("/sign-up")
    public ResponseEntity<HttpStatus> signUpUser(@RequestBody String requestBody) {
        try {
            SignUpRequest signUpRequest = objectMapperUtil.mapRequestBody(requestBody, SignUpRequest.class);
            authController.registerUser(signUpRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@RequestBody String requestBody) {
        try {
            LoginRequest loginRequest = objectMapperUtil.mapRequestBody(requestBody, LoginRequest.class);
            authController.loginUser(loginRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
