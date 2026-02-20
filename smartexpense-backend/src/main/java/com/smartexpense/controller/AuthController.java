package com.smartexpense.controller;

import com.smartexpense.dto.*;
import com.smartexpense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestDTO registerRequestDTO,
            UriComponentsBuilder uriBuilder) {

        var usuario = userService.register(registerRequestDTO);

        var uri = uriBuilder
                .path("/auth/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new ResgisterResponseDTO(
                        usuario.getId(),
                        usuario.getEmail(),
                        usuario.getRole()
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {

        var response = userService.login(body);
        return ResponseEntity.ok(response);
    }
}

