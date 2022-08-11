package com.example.favouritePlaceInTheWorld.config;

import com.example.favouritePlaceInTheWorld.model.enums.RoleEnum;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.websocket.Endpoint;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // with this line we allow access to all static resources.
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                // with this line we allow access to the home, login and register page for everyone.
                .antMatchers("/", "/users/login", "/users/register", "/about", "/maintenance").permitAll()
                .antMatchers("/actuator/**").permitAll()
                // we permit page only for admin users
                .antMatchers("/statistics").hasRole(RoleEnum.ADMIN.name())
                // next we forbid all other pages for unauthenticated users
                .antMatchers("/**").authenticated()

                .and()
                // configure login with HTML form with two input fields
                .formLogin()
                // our login page is located at http://<serverAddress>:<port>/users/login
                .loginPage("/users/login")
                // this is the name of the <input...> in the login form where user enters her email/username
                // the value of this input will be presented to our User details service.
                // default value of this parameter is username
                //.usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .usernameParameter("username")
                // this is the name of the <input...> in the login form where user enters her password
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // The place where we should land in case that login is successfully
                .defaultSuccessUrl("/home")
                // The place where we should land in case that login is unsuccessfully
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                // This is the URL which Spring will implement for me and will log the user out
                .logoutUrl("/users/logout")
                // Where to go after log out
                .logoutSuccessUrl("/")
                // Remove the session from server
                .invalidateHttpSession(true)
                // Delete the cookie that references my session
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * This give Spring two important components.
         * 1. Our userDetails service, that translates username/email to UserDetails.
         * 2. Password Encoder the component that can decide if the user password matches.
         */
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
