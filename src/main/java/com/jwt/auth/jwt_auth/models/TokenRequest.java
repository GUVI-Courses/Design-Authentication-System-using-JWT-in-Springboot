package com.jwt.auth.jwt_auth.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TokenRequest {
    private String email;
    private String password;
}
