package com.java.security.digest.resource;

import com.java.security.digest.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api")
public class MessageResource {

    @GetMapping("/public")
    public Message publicMessageResource() {
        LOGGER.debug("Spring BasicAuth: Public [publicMessageResource()]");
        return Message.builder()
                .textMessage("Public resource")
                .build();
    }

    @GetMapping("/private")
    public Message privateMessageResource(Principal principal) {
        LOGGER.debug("Spring BasicAuth: Private [privateMessageResource()]");
        return Message.builder()
                .principal(principal.getName())
                .textMessage("Private resource -> Access: User & Admin")
                .build();
    }

    @GetMapping("/admin")
    public Message adminMessageResource(Principal principal) {
        LOGGER.debug("Spring BasicAuth: Admin [adminMessageResource()]");
        return Message.builder()
                .principal(principal.getName())
                .textMessage("Admin resource -> Access: Admin")
                .build();
    }
}