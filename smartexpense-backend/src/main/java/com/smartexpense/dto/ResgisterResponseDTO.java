package com.smartexpense.dto;

import com.smartexpense.domain.enums.UserRole;

public record ResgisterResponseDTO(
        Long id,
        String username,
        UserRole role
) {
}
