package com.java.security.jwt.properties.nested;

import lombok.Data;

@Data
public class JwtAuthConfigs {
    private String authLoginUrl;
    private String jwtSecret;
}