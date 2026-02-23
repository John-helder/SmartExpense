package com.smartexpense.service;

import com.smartexpense.domain.model.User;
import com.smartexpense.dto.LoginRequestDTO;
import com.smartexpense.dto.LoginResponseDTO;
import com.smartexpense.dto.RegisterRequestDTO;
import com.smartexpense.dto.RegisterResponseDTO;
import com.smartexpense.infra.exception.BusinessException;
import com.smartexpense.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(TokenService tokenService, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponseDTO register(RegisterRequestDTO dados){
        if (this.userRepository.findByEmail(dados.email()).isPresent()){
            throw new BusinessException("Email já cadastrado");
        }
        String encryptedPassword = passwordEncoder.encode(dados.password());
        User newUser = new User(dados, encryptedPassword);
        userRepository.save(newUser);

        return new RegisterResponseDTO(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail()
        );
    }
    public LoginResponseDTO login(LoginRequestDTO dados) {

        var user = new UsernamePasswordAuthenticationToken(
                dados.email(),
                dados.password()
        );
        var auth = authenticationManager.authenticate(user);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token);
        return loginResponseDTO;
    }


}
