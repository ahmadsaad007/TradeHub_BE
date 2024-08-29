package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.boundary.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.Message;
import org.saad.tradehub_be.boundary.request.UserProfileUpdate;
import org.saad.tradehub_be.services.UserControlService;
import org.saad.tradehub_be.entity.data.actors.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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

    @PostMapping("/messaging")
    public ResponseEntity<String> sendMessage(@RequestBody String requestBody) throws Exception {
        Message message = objectMapperUtil.mapRequestBody(requestBody, Message.class);
        userController.sendMessage(message);
        return ResponseEntity.ok("Message Sent");
    }
}
