package org.saad.tradehub_be.boundary.request;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String itemId;
    private String messageText;
    private String sender;
    private String receiver;
    private Date timeStamp;
}
