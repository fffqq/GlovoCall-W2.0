package com.glovoCall.demo.UsersPack;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface userInterface extends JpaRepository<User, UUID> {



}
