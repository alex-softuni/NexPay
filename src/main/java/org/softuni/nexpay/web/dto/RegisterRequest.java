package org.softuni.nexpay.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotNull
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 symbols")
    private String username;

    @NotNull
    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    @Size(min = 6, max = 60, message = "Password must be at least 6 symbols")
    private String password;

}
