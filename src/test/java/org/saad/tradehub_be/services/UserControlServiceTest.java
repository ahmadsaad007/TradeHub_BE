package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.boundary.request.MessageForm;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.entity.data.Message;
import org.saad.tradehub_be.entity.data.User;
import org.saad.tradehub_be.repository.MessageRepository;
import org.saad.tradehub_be.repository.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControlServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private UserControlService userControlService;

    @Test
    public void testUpdateProfile_withValidData() {
        User mockUser = new User();
        mockUser.setEmail("old@example.com");

        UserProfileUpdate updateProfile = new UserProfileUpdate();
        updateProfile.setEmail("new@example.com");

        when(userRepository.findById("user123")).thenReturn(Optional.of(mockUser));

        userControlService.updateProfile("user123", updateProfile);

        verify(userRepository, times(1)).save(mockUser);
        assertEquals("new@example.com", mockUser.getEmail());
    }

    @Test
    public void testUpdateProfile_withInvalidUserId_throwsException() {
        when(userRepository.findById("user123")).thenReturn(Optional.empty());

        UserProfileUpdate updateProfile = new UserProfileUpdate();

        assertThrows(RuntimeException.class, () -> userControlService.updateProfile("user123", updateProfile));
    }

    @Test
    public void testSendMessage_withValidData() {
        MessageForm messageForm = new MessageForm();
        messageForm.setSender("user1");
        messageForm.setReceiver("user2");
        messageForm.setItemId("item123");
        messageForm.setMessageText("Hello!");

        userControlService.sendMessage(messageForm);

        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void testSendMessage_withNullSender_throwsException() {
        MessageForm messageForm = new MessageForm();
        messageForm.setReceiver("user2");

        assertThrows(IllegalArgumentException.class, () -> userControlService.sendMessage(messageForm));
    }

    @Test
    public void testViewMessages_withValidSenderReceiver() {
        userControlService.viewMessages("sender123", "receiver123");

        verify(messageRepository, times(1)).findBySenderAndReceiver("sender123", "receiver123");
    }

    @Test
    public void testViewReceivedMessages_withValidReceiver() {
        userControlService.viewReceivedMessages("receiver123");

        verify(messageRepository, times(1)).findByReceiver("receiver123");
    }
}
