package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.LoginRequest;
import org.saad.tradehub_be.boundary.request.SignUpRequest;
import org.saad.tradehub_be.entity.data.actors.UserRepository;
import org.saad.tradehub_be.entity.data.actors.User;
import org.springframework.stereotype.Service;

/**
 * The UserAuthentication class is responsbile for performing Signup and login tasks
 */
@Service
public class AuthControlService {

    private final UserRepository userRepository;

    public AuthControlService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns true if User is successfully able to login
     * This method would be called when  where the user selects login option
     *
     * @param loginUser  is the Boundary Object that provides the LoginData supplied by the FE
     * @return boolean true or false if the user was successfully logged in or not
     */
    public boolean loginUser(LoginRequest loginUser) {
        if(loginUser.getUsername() != null && loginUser.getPassword() != null) {
            User user = userRepository.findByUsername(loginUser.getUsername());
            if(user != null && user.getPassword().equals(loginUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if User is successfully able to register a new Account
     * This method would be called when  where the user selects signup option
     *
     * @param signUpRequest  is the Boundary Object that provides the SignUp Info supplied by the FE to the BE
     * @return boolean true or false if the user was successfully registered or not
     */
    public boolean registerUser(SignUpRequest signUpRequest) {
        //TODO this method should have an exception handler
        if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(signUpRequest.getPassword());
            user.setEmail(signUpRequest.getEmail());

            userRepository.save(user);
            return true;
        }
        return false;
    }
}
