package com.smartexpense.dto;

import com.smartexpense.domain.enums.UserRole;

public record RegisterRequestDTO(
        String nome,
        String password,
        String email,
        UserRole role
) {
}
