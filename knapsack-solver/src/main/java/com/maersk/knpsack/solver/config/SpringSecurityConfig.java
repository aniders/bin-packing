package com.maersk.knpsack.solver.config;

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
        auth.inMemoryAuthentication().withUser("app").password("aniders").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("admin").password("aniders").roles("ACTUATOR");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
         http
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/resources/**").permitAll()
          .antMatchers("/solve/*").hasRole("ADMIN")
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