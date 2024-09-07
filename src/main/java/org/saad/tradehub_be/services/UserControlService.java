package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.Message;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.entity.data.actors.User;
import org.saad.tradehub_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserControlService {

    private final UserRepository userRepository;

    public UserControlService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateProfile(String userId, UserProfileUpdate userProfileUpdate) {
    }

    public User getUserProfile(String userId) {
        return userRepository.findByUserId(userId);
    }

    public void sendMessage(Message message) {
    }
}
