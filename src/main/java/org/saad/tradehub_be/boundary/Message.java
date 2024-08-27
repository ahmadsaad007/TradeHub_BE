package org.saad.tradehub_be.boundary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
