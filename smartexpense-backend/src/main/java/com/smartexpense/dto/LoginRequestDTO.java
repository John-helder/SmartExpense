package com.smartexpense.dto;

public record LoginRequestDTO(
        String email,
        String password
) {
}
