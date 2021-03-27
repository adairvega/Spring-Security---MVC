package com.utez.AppSecurityJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.utez.AppSecurityJPA.security.service.MiDetalleUsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
   @Autowired
   MiDetalleUsuarioService userService;
   
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // TODO Auto-generated method stub
      
      auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
   }
   
   @Bean
   public DaoAuthenticationProvider AuthenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // TODO Auto-generated method stub
      http
         .authorizeRequests()
            .antMatchers("/","/home").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
         .logout()
            .permitAll();
   }
   
//   @Bean
//   @Override
//   protected UserDetailsService userDetailsService() {
//      UserBuilder builder = User.withDefaultPasswordEncoder();
//      UserDetails user = builder
//            .username("adair")
//            .password("root")
//            .roles("USER")
//            .build();
//      UserDetails admin = builder
//            .username("admin")
//            .password("root")
//            .roles("ADMIN")
//            .build();
//      
//      return new InMemoryUserDetailsManager(user, admin);
//   }
}
