package com.agenciaviagem.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AutenticacaoService autenticacaoService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AutenticacaoService autenticacaoService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = autenticacaoService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
