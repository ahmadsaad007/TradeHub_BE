package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.boundary.request.MessageForm;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.data.Message;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.MessageRepository;
import org.saad.tradehub_be.repository.UserRepository;

import java.util.Date;
import java.util.List;
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

    @Test
    public void testViewMessages_withNoMessages_returnsEmptyList() {
        List<Message> messages = messageRepository.findBySenderAndReceiver("user1", "user3");
        assertTrue(messages.isEmpty(), "No messages should be found between user1 and user3");
    }

    @Test
    public void testViewReceivedMessages_withEmptyReceiver_throwsException() {
        MessageForm messageForm = new MessageForm();
        messageForm.setSender("user1");

        assertThrows(IllegalArgumentException.class, () -> userControlService.sendMessage(messageForm));
    }

    @Test
    public void testViewReceivedMessages_withValidReceiver_returnsMessages() {
        Message message1 = new Message();
        message1.setSender("user1");
        message1.setReceiver("receiver123");
        message1.setMessageText("Are you available?");

        when(messageRepository.findByReceiver("receiver123"))
                .thenReturn(List.of(message1));

        List<Message> messages = userControlService.viewReceivedMessages("receiver123");

        assertFalse(messages.isEmpty(), "Messages should be found for receiver123");
        assertEquals("Are you available?", messages.get(0).getMessageText(), "Message text should match");
    }

    @Test
    public void testViewMessages_withValidUsers_returnsMessages() {
        Message message1 = new Message();
        message1.setSender("sender123");
        message1.setReceiver("receiver123");
        message1.setMessageText("Hello!");
        message1.setTimeStamp(new Date());

        Message message2 = new Message();
        message2.setSender("receiver123");
        message2.setReceiver("sender123");
        message2.setMessageText("Hi!");
        message2.setTimeStamp(new Date());

        when(messageRepository.findBySenderAndReceiver("sender123", "receiver123"))
                .thenReturn(List.of(message1));
        when(messageRepository.findBySenderAndReceiver("receiver123", "sender123"))
                .thenReturn(List.of(message2));

        List<Message> messages = userControlService.viewMessages("sender123", "receiver123");

        assertEquals(2, messages.size(), "Two messages should be found in the conversation");
        assertEquals("Hello!", messages.get(0).getMessageText(), "First message should match");
        assertEquals("Hi!", messages.get(1).getMessageText(), "Second message should match");
    }

}
