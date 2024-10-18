package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.MessageForm;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.data.Message;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.MessageRepository;
import org.saad.tradehub_be.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class UserControlService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public UserControlService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Updates the profile of the user based on the provided UserProfileUpdate object.
     *
     * @param username            the ID of the user to update
     * @param userProfileUpdate the object containing the updated profile information
     */
    @Transactional
    public void updateProfile(String username, UserProfileUpdate userProfileUpdate) {
        // Fetch the user by userId
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));

        // Update user profile fields if provided in the UserProfileUpdate object
        if (userProfileUpdate.getEmail() != null && !userProfileUpdate.getEmail().isEmpty()) {
            user.setEmail(userProfileUpdate.getEmail());
        }
        if (userProfileUpdate.getPhoneNumber() != null && !userProfileUpdate.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(userProfileUpdate.getPhoneNumber());
        }
        if (userProfileUpdate.getAddress() != null && !userProfileUpdate.getAddress().isEmpty()) {
            user.setAddress(userProfileUpdate.getAddress());
        }
        if (userProfileUpdate.getZipCode() != null && !userProfileUpdate.getZipCode().isEmpty()) {
            user.setZipCode(userProfileUpdate.getZipCode());
        }

        userRepository.save(user);
    }

    /**
     * Retrieves the profile of a user based on the provided userId.
     *
     * @param username the ID of the user to fetch
     * @return the User object representing the user's profile
     */
    public User getUserProfile(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Sends a message from one user to another.
     *
     * @param messageForm the MessageForm containing the message details
     */
    public void sendMessage(MessageForm messageForm) {
        if (messageForm.getSender() == null || messageForm.getReceiver() == null) {
            throw new IllegalArgumentException("Message must have both sender and recipient");
        }

        Message message = new Message();
        message.setId(UUID.randomUUID().toString()); // Generate a unique ID for the message
        message.setItemId(messageForm.getItemId());
        message.setMessageText(messageForm.getMessageText());
        message.setSender(messageForm.getSender());
        message.setReceiver(messageForm.getReceiver());
        message.setTimeStamp(messageForm.getTimeStamp());
        messageRepository.save(message);
    }

    /**
     * Retrieves messages between two users.
     *
     * @param sender   the sender of the messages
     * @param receiver the receiver of the messages
     * @return List of messages exchanged between the sender and receiver
     */
    public List<Message> viewMessages(String sender, String receiver) {
        List<Message> messagesSent = messageRepository.findBySenderAndReceiver(sender, receiver);
        List<Message> messagesReceived = messageRepository.findBySenderAndReceiver(receiver, sender);
        return buildConversation(messagesSent, messagesReceived);
    }

    private List<Message> buildConversation(List<Message> messagesSent, List<Message> messagesReceived) {
        List<Message> conversation = new ArrayList<>();
        conversation.addAll(messagesSent);
        conversation.addAll(messagesReceived);

        // Sort the combined list by timeStamp so the messages appear in chronological order
        conversation.sort(Comparator.comparing(Message::getTimeStamp));
        return conversation;
    }

    /**
     * Retrieves all messages received by a user.
     *
     * @param receiver the receiver of the messages
     * @return List of messages received by the user
     */
    public List<Message> viewReceivedMessages(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }

}
