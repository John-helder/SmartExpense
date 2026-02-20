package com.smartexpense.dto;

import com.smartexpense.domain.enums.UserRole;

public record UserResgiterDTO(
        String nome,
        String password,
        String email,
        UserRole role,
        Boolean ativo
) {
}
