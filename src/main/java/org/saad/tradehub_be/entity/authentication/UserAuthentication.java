package org.saad.tradehub_be.entity.authentication;

import org.saad.tradehub_be.boundary.LoginRequestDTO;
import org.saad.tradehub_be.boundary.SignUpRequestDTO;
import org.saad.tradehub_be.entity.data.actors.UserRepository;
import org.saad.tradehub_be.entity.data.actors.User;

/**
 * The UserAuthentication class is responsbile for performing Signup and login tasks
 */
public class UserAuthentication {

    private final UserRepository userRepository;

    public UserAuthentication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns true if User is successfully able to login
     * This method would be called when  where the user selects login option
     *
     * @param loginUser  is the Boundary Object that provides the LoginData supplied by the FE
     * @return boolean true or false if the user was successfully logged in or not
     */
    public boolean loginUser(LoginRequestDTO loginUser) {
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
     * @param signUpRequestDTO  is the Boundary Object that provides the SignUp Info supplied by the FE to the BE
     * @return boolean true or false if the user was successfully registered or not
     */
    public boolean registerUser(SignUpRequestDTO signUpRequestDTO) {
        //TODO this method should have an exception handler
        if(userRepository.findByUsername(signUpRequestDTO.getUsername()) != null) {
            User user = new User();
            user.setUsername(signUpRequestDTO.getUsername());
            user.setPassword(signUpRequestDTO.getPassword());
            user.setEmail(signUpRequestDTO.getEmail());

            userRepository.save(user);
            return true;
        }
        return false;
    }
}
