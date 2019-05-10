package com.java.security.digest.properties.nested;

import lombok.Data;

@Data
public class DigestAuthConfigs {
    private String realmName;
    private String secretKey;
}
