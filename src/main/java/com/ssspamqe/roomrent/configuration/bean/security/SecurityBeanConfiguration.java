package com.ssspamqe.roomrent.configuration.bean.security;

import com.ssspamqe.roomrent.domain.dao.interfaces.UserDAO;
import com.ssspamqe.roomrent.security.entities.SecurityUserDetails;
import com.ssspamqe.roomrent.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityBeanConfiguration {

    @Bean
    public UserDetailsService userDetailsService(UserDAO userDAO, SecurityUserService securityUserService) {
        return username -> {
            var user = userDAO.getByName(username);
            var hashedPassword = securityUserService.getPasswordHashByUserId(user.getId());
            return SecurityUserDetails.builder()
                    .user(user)
                    .passwordHash(hashedPassword)
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
