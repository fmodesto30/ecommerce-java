package com.management.domain.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public TokenValidationService(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public Boolean isTokenValid(String jsonData) throws JSONException {
        JSONObject obj = new JSONObject(jsonData);
        String authHeader = obj.getJSONObject("headers").getString("Authorization");
        String token = authHeader.replace("Bearer ", "");
        
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtService.validateToken(token, userDetails);
    }
    
}