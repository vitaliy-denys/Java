package com.java.security.digest.properties;

import com.java.security.digest.properties.nested.DigestAuthConfigs;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class ApplicationProperties {
    private DigestAuthConfigs digestAuthConfigs;
}
