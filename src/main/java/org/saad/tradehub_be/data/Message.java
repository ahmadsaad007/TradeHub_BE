package org.saad.tradehub_be.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The Message class represents the Message Interaction between buyer and seller
 * regarding a particular itemId
 * <p>
 * UseCase: Buyer messages Seller and vice versa
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    private String id;
    private String itemId;
    private String messageText;
    private String sender;
    private String receiver;
    private Date timeStamp;
}
