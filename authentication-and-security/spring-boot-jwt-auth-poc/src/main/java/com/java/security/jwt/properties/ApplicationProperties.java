package com.java.security.jwt.properties;

import com.java.security.jwt.properties.nested.JwtAuthConfigs;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class ApplicationProperties {
    private JwtAuthConfigs jwtAuthConfigs;
}