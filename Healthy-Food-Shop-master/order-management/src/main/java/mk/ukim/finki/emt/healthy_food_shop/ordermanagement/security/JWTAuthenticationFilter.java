package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.security;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.exceptions.PasswordsNotTheSameException;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static mk.ukim.finki.emt.healthy_food_shop.ordermanagement.security.SecurityConstants.*;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

            try {
                UserDetails user = userDetailsService.loadUserByUsername(creds.getUsername());
                if (!passwordEncoder.matches(creds.getPassword(), user.getPassword())) {
                    throw new PasswordsNotTheSameException();
                }
            } catch (UserNotFoundException u) {
                throw new UserNotFoundException();
            }

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            creds.getAuthorities()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        UserDetails userDetails = (mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.User) auth.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.getWriter().append(token);
    }

}