package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.entity.data.Message;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.MessageForm;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.services.UserControlService;
import org.saad.tradehub_be.entity.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private UserControlService userController;

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateProfile(@RequestBody String requestBody, @RequestParam("uid") String userId) {
        try {
            UserProfileUpdate userProfileUpdate = objectMapperUtil.mapRequestBody(requestBody, UserProfileUpdate.class);
            userController.updateProfile(userId, userProfileUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/about/{userId}")
    public ResponseEntity<User> viewProfile(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(userController.getUserProfile(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/messages/send")
    public ResponseEntity<String> sendMessage(@RequestBody String requestBody) throws Exception {
        MessageForm messageForm = objectMapperUtil.mapRequestBody(requestBody, MessageForm.class);
        userController.sendMessage(messageForm);
        return ResponseEntity.ok("Message Sent");
    }

    @PostMapping("/messages/view")
    public ResponseEntity<List<Message>> viewMessages(@RequestBody String requestBody) throws Exception {
        MessageForm messageForm = objectMapperUtil.mapRequestBody(requestBody, MessageForm.class);

        List<Message> messages = userController.viewMessages(messageForm.getSender(), messageForm.getReceiver());
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/messages/view/received")
    public ResponseEntity<List<Message>> viewReceivedMessages(@RequestParam String receiver) {
        // Call service to view received messages
        List<Message> messages = userController.viewReceivedMessages(receiver);
        return ResponseEntity.ok(messages);
    }

}
