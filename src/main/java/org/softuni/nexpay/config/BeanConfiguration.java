package org.softuni.nexpay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
