package io.github.luanleiteleao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // altentica: altentica um usuário
        auth
        .inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("user")
        .password(passwordEncoder().encode("user"))
        .roles("USER","ADMIM");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // altorização: valida se um usuário tem autorização para acessar um determinado path
        http
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/api/clientes/**")
                .hasAnyRole("USER","ADMIM")
            .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER","ADMIM")
            .antMatchers("/api/produtos/**")
                .hasRole("ADMIM")
            .and()
                .httpBasic();
    }

}
