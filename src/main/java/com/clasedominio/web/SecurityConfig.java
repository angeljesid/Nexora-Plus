package com.clasedominio.web;

import com.clasedominio.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
public DaoAuthenticationProvider authProvider(UsuarioDetailsService service) {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(service);
    auth.setPasswordEncoder(passwordEncoder());
    return auth;
}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider authProvider) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) 
            .authenticationProvider(authProvider)

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/imagenes/**").permitAll()
                .requestMatchers("/login").permitAll()

                .requestMatchers("/vendedor/**").hasRole("VENDEDOR")
                .requestMatchers("/bodega/**", "/admin/productos/**").hasAnyRole("BODEGUERO", "ADMIN")
                .requestMatchers("/secretaria/**").hasAnyRole("SECRETARIA", "ADMIN")

                .requestMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/procesar-login") 
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}