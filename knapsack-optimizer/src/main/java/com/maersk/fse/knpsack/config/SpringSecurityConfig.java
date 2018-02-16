package com.maersk.fse.knpsack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("user2@test.com").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("actuator").password("aniders").roles("ACTUATOR");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
         http
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/resources/**").permitAll()
          .anyRequest().authenticated()
        .and()
        .httpBasic();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("swagger-ui.htm")
        .antMatchers(HttpMethod.GET);
    }
}