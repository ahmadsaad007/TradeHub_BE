package org.saad.tradehub_be.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.saad.tradehub_be.entity.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private Message mockMessage;

    @BeforeEach
    public void setUp() {
        mockMessage = Message.builder()
                .id("msg123")
                .itemId("item123")
                .messageText("Test Message")
                .sender("user123")
                .receiver("user456")
                .timeStamp(new Date())
                .build();

        messageRepository.save(mockMessage);
    }

    @Test
    public void testFindBySenderAndReceiver_withValidSenderAndReceiver_returnsMessages() {
        List<Message> foundMessages = messageRepository.findBySenderAndReceiver("user123", "user456");

        assertFalse(foundMessages.isEmpty());
        assertEquals(1, foundMessages.size());
        assertEquals("Test Message", foundMessages.get(0).getMessageText());
    }

    @Test
    public void testFindByReceiver_withValidReceiver_returnsMessages() {
        List<Message> foundMessages = messageRepository.findByReceiver("user456");

        assertFalse(foundMessages.isEmpty());
        assertEquals(1, foundMessages.size());
        assertEquals("Test Message", foundMessages.get(0).getMessageText());
    }

    @Test
    public void testFindByReceiver_withInvalidReceiver_returnsEmptyList() {
        List<Message> foundMessages = messageRepository.findByReceiver("invalidUser");

        assertTrue(foundMessages.isEmpty());
    }
}
