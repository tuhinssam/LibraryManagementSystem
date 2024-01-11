package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.services.SecurityService;
import com.example.librarymanagementsystem.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("student")
//                .password(passwordEncoder.encode("student123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("STUDENT", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
        return new SecurityService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        .antMatchers(HttpMethod.GET, "/student/**").hasAuthority(Constants.STUDENT_SELF_INFO_AUTHORITY)
//                .antMatchers(HttpMethod.GET, "/student-by-id/**").hasAuthority(Constants.STUDENT_INFO_AUTHORITY)
//                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY)
//                .antMatchers(HttpMethod.GET, "/book/**").hasAuthority(Constants.READ_BOOK_AUTHORITY)
//                .antMatchers(HttpMethod.POST, "/book/**").hasAuthority(Constants.CREATE_BOOK_AUTHORITY)
//                .antMatchers("/transaction/payment/**").hasAuthority(Constants.MAKE_PAYMENT_AUTHORITY)
//                .antMatchers("/transaction/**").hasAuthority(Constants.INITIATE_TRANSACTION_AUTHORITY)
//                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin();
       return http.httpBasic().and()
               .csrf().disable()
               .authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/student/**").hasAuthority(Constants.STUDENT_SELF_INFO_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/student-by-id/**").hasAuthority(Constants.STUDENT_INFO_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/book/**").hasAuthority(Constants.READ_BOOK_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/book/**").hasAuthority(Constants.CREATE_BOOK_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers("/transaction/payment/**").hasAuthority(Constants.MAKE_PAYMENT_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers("/transaction/**").hasAuthority(Constants.INITIATE_TRANSACTION_AUTHORITY)
               .and()
               .authorizeHttpRequests().requestMatchers("/**").permitAll().and()
               .formLogin().and()
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
