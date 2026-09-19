package com.glovoCall.demo.UsersPack;


import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class userController {
    private final userService userService;
    private final userInterface userInterface;

    @PostMapping("/PostRest")
    public void createUser(@RequestBody
                           @Valid
                           User user)
    {
        userService.createUser(user);

    }
}
