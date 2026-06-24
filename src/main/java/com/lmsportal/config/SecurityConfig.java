package com.lmsportal.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lmsportal.enums.RoleEnum;
import com.lmsportal.repositories.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public SecurityConfig(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Boot 3 / Security 6 style AuthenticationManager provider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:*", "http://127.0.0.1:*","http://localhost:55045:*"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtProvider, userDetailsService());

        http
        .cors(cors -> {})
        .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/videos/**").permitAll()
                        .requestMatchers("/api/chatbot/**").permitAll()
                        .requestMatchers(
                                "/api/product/v1/checkout",
                                "/api/payments/confirm"
                            ).permitAll()
                        .requestMatchers("/api/videos/**").permitAll()

                        .requestMatchers(
                        	    "/payment-success",
                        	    "/payment-cancel",
                        	    "/api/payments/payment-success",
                        	    "/api/payments/payment-cancel"
                        	).permitAll()
                        
//                        .requestMatchers("/api/admin/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
                        //.requestMatchers("/api/instructor/**").hasRole(RoleEnum.INSTRUCTOR.name())
                        .requestMatchers("/api/instructor/**"). hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
//                        .requestMatchers("/api/admin/**").permitAll()
//                        .requestMatchers("/api/instructor/**").permitAll()
                        //.requestMatchers("/api/student/**").hasRole(RoleEnum.STUDENT.name())
                       //.requestMatchers("/api/student/**").hasAnyRole("STUDENT")
                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
