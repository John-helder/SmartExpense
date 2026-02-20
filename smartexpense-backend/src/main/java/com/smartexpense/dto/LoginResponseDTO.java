package com.smartexpense.dto;

public record LoginResponseDTO(
        String password,
        String email,
        String role
) {
}
