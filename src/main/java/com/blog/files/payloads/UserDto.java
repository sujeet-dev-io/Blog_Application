package com.blog.files.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    @NotBlank(message = "user name must not be blank")
    private String name;
    @NotEmpty(message = "user email must not empty")

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email should be valid and follow the pattern [a-z0-9._%+-]@[a-z0-9.-].[a-z]{2,3}")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")

    private String password;
    @NotEmpty(message = "about must not empty")
    private String about;
    private LocalDateTime created;
}
