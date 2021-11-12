package com.analisecredito.security.jwt;

import com.analisecredito.security.usersecurity.CredencialDTO;
import com.analisecredito.security.usersecurity.UserSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){this.authenticationManager = authenticationManager;}

    @Autowired
    public void setJWTUtil(JWTUtil jwtUtil){this.jwtUtil = jwtUtil;}




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        try{
            CredencialDTO cred = new ObjectMapper()
                    .readValue(request.getInputStream(), CredencialDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getSenha(), new ArrayList<>());
            return authenticationManager.authenticate(authToken);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth){
        String username = ((UserSecurity) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer "+ token);
        res.addHeader("access-control-expose-headers", "Authorization");
    }
}
