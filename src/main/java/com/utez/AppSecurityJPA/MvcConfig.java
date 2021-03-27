package com.utez.AppSecurityJPA;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Controller
public class MvcConfig implements WebMvcConfigurer {
   
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/home").setViewName("home");
      registry.addViewController("/").setViewName("home");
      registry.addViewController("/hola").setViewName("hola");
      registry.addViewController("/login").setViewName("login");
   }
   
   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/bienvenida")
   public String bienvenida() {
      return "hola";
   }
}