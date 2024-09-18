package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.errorhandler.UserAlreadyExistsException;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.LoginRequest;
import org.saad.tradehub_be.boundary.request.SignUpRequest;
import org.saad.tradehub_be.services.AuthControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUpUser(@RequestBody String requestBody) {
        try {
            SignUpRequest signUpRequest = objectMapperUtil.mapRequestBody(requestBody, SignUpRequest.class);
            authController.registerUser(signUpRequest);

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);

        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for user login.
     */
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody String requestBody) {
        try {
            LoginRequest loginRequest = objectMapperUtil.mapRequestBody(requestBody, LoginRequest.class);

            if (authController.loginUser(loginRequest)) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
