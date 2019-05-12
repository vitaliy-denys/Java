package com.java.security.jwt.configuration;

import com.java.security.jwt.constants.SecurityConstants;
import com.java.security.jwt.properties.ApplicationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ApplicationProperties applicationProperties;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationProperties applicationProperties) {
        this.authenticationManager = authenticationManager;
        this.applicationProperties = applicationProperties;
        setFilterProcessesUrl(applicationProperties.getJwtAuthConfigs().getAuthLoginUrl());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String jsonString = request.getReader().lines().collect(Collectors.joining());
            JSONObject jsonObject = new JSONObject(jsonString);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jsonObject.get("username"), jsonObject.get("password"));
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException | JSONException e) {
            throw new AuthenticationCredentialsNotFoundException("Cannot attempt Authentication", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        User principalUser = (User) authentication.getPrincipal();
        List<String> roles = principalUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        byte[] signingKey = applicationProperties.getJwtAuthConfigs().getJwtSecret().getBytes();
        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(principalUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact();
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}