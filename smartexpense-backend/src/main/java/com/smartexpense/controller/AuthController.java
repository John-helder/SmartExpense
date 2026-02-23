package com.smartexpense.controller;

import com.smartexpense.dto.*;
import com.smartexpense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody RegisterRequestDTO registerRequestDTO,
            UriComponentsBuilder uriBuilder) {

        var response = userService.register(registerRequestDTO);

        var uri = uriBuilder
                .path("/auth/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {

        var response = userService.login(body);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager/teste")
    public String testeManager() {
        return "Acesso liberado para MANAGER";
    }

}

