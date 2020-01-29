package edu.otaviotarelho.userservice.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.otaviotarelho.userservice.domain.User;
import edu.otaviotarelho.userservice.exception.UserAuthenticationRequestParserException;
import edu.otaviotarelho.userservice.secrets.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static edu.otaviotarelho.userservice.secrets.Constants.HEADER_STRING;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            new UserAuthenticationRequestParserException(e);
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token =  JWT.create()
                        .withSubject(((User) authResult.getPrincipal()).getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                        .sign(Algorithm.HMAC512(Constants.SECRET.getBytes()));

        response.addHeader(HEADER_STRING, Constants.TOKEN_PREFIX + token);

    }
}
