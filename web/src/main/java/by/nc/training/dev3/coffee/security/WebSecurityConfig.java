package by.nc.training.dev3.coffee.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Win on 30.05.2017.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/bill/**").hasRole("USER")
                .antMatchers("/client/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilterAt(new CustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .anonymous().disable().exceptionHandling()
                .authenticationEntryPoint(new org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint("headerValue"))
                .and()

                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/", true).permitAll()
                .and()

                .logout().deleteCookies("JSESSIONID");


    }

}
