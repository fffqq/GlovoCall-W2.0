package com.glovoCall.demo.UsersPack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class userService {

    private final userInterface userInterface;
    private final Logger
            LOGGER=LoggerFactory.getLogger(userService.class);

    public void createUser(User user){
        LOGGER.info("Creating user started");
        userInterface.save(user);
    }
}
