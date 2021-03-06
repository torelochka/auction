package ru.itis.akchurina.auction.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        @Qualifier("UserDetailsServiceImpl")
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                        .csrf()
                                .ignoringAntMatchers("/rest/**").and()
                        .authorizeRequests()
                                .antMatchers("/profile", "/auction/create", "/auction/*/bet").authenticated().and()
                        .formLogin()
                                .loginPage("/signIn")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/profile")
                                .failureUrl("/signIn?error").and()
                        .logout()
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .logoutSuccessUrl("/signIn")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
}
