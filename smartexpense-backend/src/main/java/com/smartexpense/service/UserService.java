package com.smartexpense.service;

import com.smartexpense.domain.model.User;
import com.smartexpense.dto.UserResgiterDTO;
import com.smartexpense.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TokenService tokenService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserResgiterDTO dados){
        if (this.userRepository.findByEmail(dados.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }
        String encryptedPassword = passwordEncoder.encode(dados.password());
        User newUser = new User(dados, encryptedPassword);
        this.userRepository.save(newUser);
        return newUser;
    }
}
