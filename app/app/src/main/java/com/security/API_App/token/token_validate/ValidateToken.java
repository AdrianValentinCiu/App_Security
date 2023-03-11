package com.security.API_App.token.token_validate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.security.API_App.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ValidateToken {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    public User user;

    public ValidateToken(String token,
                         LocalDateTime createdAt,
                         LocalDateTime expiresAt,
                         User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
