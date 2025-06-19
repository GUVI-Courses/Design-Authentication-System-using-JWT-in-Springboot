package com.jwt.auth.jwt_auth.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TokenResponse {
    private String jwtToken;
    private String username;
}
