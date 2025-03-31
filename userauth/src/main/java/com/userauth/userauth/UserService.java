package com.userauth.userauth;

import javax.management.RuntimeErrorException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null || userRepository.findByUsername(userDTO.getUsername()) != null ) {
            throw new RuntimeException( "Username or email already exists!");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        return userRepository.save(user);
    }

    public User findbyUsername(UserDTO userDTO) {
       return userRepository.findByUsername(userDTO.getUsername());
    }

    public User findbyEmail(UserDTO userDTO) 
    {
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public String authenticateUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))) {
            throw new BadCredentialsException( "Invalid Password");
        } else {
            // Return JWT Token.
            return null;
        }
        
    }
    
}
