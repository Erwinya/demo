package com.example.demo.serviceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String addNewUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        User user = UserMapper.toEntity(userDTO);
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data!");
        }
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must be unique!");
        }
        return "User added successfully!";
    }


    @Override
    public UserDTO getUserByUsername(String username){
        if (username == null || username.isEmpty() || username.isBlank() || username.trim().isEmpty() || username.equals("null") || username.equals("undefined") || username.equals(" "))  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be null or empty!");
        }
        if (!userRepository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }                                          
        User user = userRepository.findByUsername(username).get();
        return UserMapper.toDTO(user);

    }
}
