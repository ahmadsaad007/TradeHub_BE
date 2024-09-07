package org.saad.tradehub_be.repository.errorhandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessage {
    private String messageText;

    public String displayMessageText() {
        return this.messageText;
    }
}
