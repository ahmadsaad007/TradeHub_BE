package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.data.Message;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.MessageForm;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.services.UserControlService;
import org.saad.tradehub_be.data.User;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateProfile(@RequestBody String requestBody, @RequestParam("username") String username) {
        try {
            UserProfileUpdate userProfileUpdate = objectMapperUtil.mapRequestBody(requestBody, UserProfileUpdate.class);
            userController.updateProfile(username, userProfileUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/about/{username}")
    public ResponseEntity<User> viewProfile(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userController.getUserProfile(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/messages/send")
    public ResponseEntity<String> sendMessage(@RequestBody String requestBody) throws Exception {
        MessageForm messageForm = objectMapperUtil.mapRequestBody(requestBody, MessageForm.class);
        userController.sendMessage(messageForm);
        return ResponseEntity.ok("Message Sent");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/messages/view")
    public ResponseEntity<List<Message>> viewMessages(@RequestBody String requestBody) throws Exception {
        MessageForm messageForm = objectMapperUtil.mapRequestBody(requestBody, MessageForm.class);

        List<Message> messages = userController.viewMessages(messageForm.getSender(), messageForm.getReceiver());
        return ResponseEntity.ok(messages);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/messages/view/received")
    public ResponseEntity<List<Message>> viewReceivedMessages(@RequestParam String receiver) {
        // Call service to view received messages
        List<Message> messages = userController.viewReceivedMessages(receiver);
        return ResponseEntity.ok(messages);
    }

}
