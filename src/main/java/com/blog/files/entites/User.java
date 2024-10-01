package com.blog.files.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("posts")
public class User implements UserDetails {

    @Id
    private String userId;

    @Schema(description = "Enter name", required = true, example = "John Doe")
    @Column(name = "name")
    @Size(
            min = 6,
            max = 18,
            message = "property '${validatedValue}' should be between {min} and {max} characters")
    private String name;

    @Schema(description = "Enter email", required = true, example = "john.doe@example.com")
    @Column(name = "email")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email should be valid and follow the pattern [a-z0-9._%+-]@[a-z0-9.-].[a-z]{2,3}")
    private String email;

    @Schema(description = "Enter password", required = true, example = "P@ssw0rd!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")
    private String password;

    @Schema(description = "Enter details about the user", required = true, example = "Software Engineer at XYZ")
    @Column(name = "about")
    private String about;

    @Schema(description = "Date when the user was created", example = "2024-09-23T10:15:30")
    @Column(name = "create_date")
    private LocalDateTime created;

    @Schema(description = "Flag to indicate if the user is deleted", example = "false")
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}