package com.userService.demo.security;

import com.userService.demo.model.User;
import com.userService.demo.repository.UserRepository;
//import com.userService.demo.security.filter.JwtAuthFilter;
import com.userService.demo.security.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public SecurityConfig(UserRepository userRepository, JwtAuthFilter jwtAuthFilter){
        this.userRepository = userRepository;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // getting the user object
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user is not found with email " + username));
            // if we have found the user in the repository we will return the UserServiceImpl object
            return new UserDetailsImpl(user);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers( "/role/home", "/user/create", "/role/create", "/user/updatePassword", "/user/generateToken").permitAll()
                                .requestMatchers("/user/home").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPPORT")
                                .anyRequest().authenticated())
                .userDetailsService(userDetailsService())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                // this is for jwt token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // this is needed as the authProvider in above function need the UserDetailService object



    // this bean is created just for the jwt token.
    // this bean was created once I started implementing the jwt token on the project
    // need to confirm if authenticating manager is needed or not for spring security if we are not using jwt token
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}