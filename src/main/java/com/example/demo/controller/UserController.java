package com.example.demo.controller;

import com.example.demo.response.CustomResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @PostMapping // eger ayni isimde databasede user varsa hata veriyor             
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<String> registerNewUser(@RequestBody UserDTO userDTO) {
        String serviceResponse = userService.addNewUser(userDTO);
        CustomResponse<String> response = new CustomResponse<String>();
        response.setData(serviceResponse);
        response.setStatusCode(200);
        response.setStatusMessage("SUCCESS");
        response.setTimestamp(Instant.now().toString());
        return response;
    }
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<UserDTO> getUserByUsername(@PathVariable String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (!userService.getUserByUsername(username).getUsername().equals(username)) {
            throw new IllegalArgumentException("User not found");
        }
        UserDTO userDTO = userService.getUserByUsername(username);
        CustomResponse<UserDTO> response = new CustomResponse<UserDTO>();
        response.setData(userDTO);
        response.setStatusCode(200);
        response.setStatusMessage("SUCCESS");
        response.setTimestamp(Instant.now().toString());
        return response;
    }
}
