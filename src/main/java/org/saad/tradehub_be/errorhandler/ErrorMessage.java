package org.saad.tradehub_be.errorhandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessage {
    private String messageText;

    public String displayMessageText() {
        return this.messageText;
    }
}
