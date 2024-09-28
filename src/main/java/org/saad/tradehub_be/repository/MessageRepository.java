package org.saad.tradehub_be.repository;


import org.saad.tradehub_be.entity.data.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findBySenderAndReceiver(String sender, String receiver);

    List<Message> findByReceiver(String receiver);
}
