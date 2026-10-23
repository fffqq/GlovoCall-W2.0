package com.glovoCall.demo.UsersPack;
import com.glovoCall.demo.JWT.JWTDTO;
import com.glovoCall.demo.JWT.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class userService {

    private final UserRepository UserRepository;
    private final Logger
            LOGGER=LoggerFactory.getLogger(userService.class);
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public void createUser(User user){
        LOGGER.info("Creating user started");
        Optional<User> copyFinder= UserRepository.findByEmailOrUsername
                (user.getEmail(), user.getUsername());
        if(copyFinder.isPresent()){
            LOGGER.error("Trying to create user with email that already have been created");
            throw new IllegalArgumentException("User with this email already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.info("Encoding Users password");
        UserRepository.save(user);
        LOGGER.info("User have been created");
    }

    public JWTDTO authentication(UserCredentialDTO userCredentialDTO) {
        User finder=UserRepository.findByEmail(userCredentialDTO.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("Not Found"));
        LOGGER.info("Auth User");
        if (passwordEncoder.matches(userCredentialDTO.getPassword(), finder.getPassword())){
            LOGGER.info("user email={}",userCredentialDTO.getEmail());
            return jwtService.generateAuthToken(userCredentialDTO.getEmail());
        }
        else
            throw new IllegalArgumentException("Wrong Password or Email");
    }

    public JWTDTO refreshAut(String token){
        if (!jwtService.validationOfJwtToken(token)){
         throw new IllegalArgumentException("Expired Refresh-token");
        }
        String email= jwtService.getEmailFromToken(token);

        return jwtService.refreshBaseToken(email,token);
    }
}
