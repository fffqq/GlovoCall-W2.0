package com.glovoCall.demo.UsersPack;


import com.glovoCall.demo.JWT.JWTDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class userController {
    private final userService userService;

    private void setJwtCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    @PostMapping("/PostRest")
    public void createUser(@RequestBody
                           @Valid
                           User user)
    {
        userService.createUser(user);
    }


    @PostMapping("/auth")
    public ResponseEntity <JWTDTO> authorization
            (@Valid @RequestBody UserCredentialDTO userCredentialDTO,
             HttpServletResponse userResponse){
        JWTDTO tokens=userService.authentication(userCredentialDTO);

        setJwtCookie(userResponse,"jwt",tokens.getToken(),60*60);
        setJwtCookie(userResponse,"refresh_jwt",tokens.getRefreshToken(),
                60*60*24);


        return ResponseEntity.ok(tokens);
    }
    @PostMapping("/refresh")
    public ResponseEntity <JWTDTO> RefrshAuthorization
            (@CookieValue(name = "refresh_jwt") String refreshToken,
             HttpServletResponse response){
        JWTDTO tokens= userService.refreshAut(refreshToken);

        setJwtCookie(response,"jwt",tokens.getToken(),60*60);
        setJwtCookie(response,"refresh_jwt",tokens.getRefreshToken(),
                60*60*24);
        return ResponseEntity.ok(tokens);
    }


}
