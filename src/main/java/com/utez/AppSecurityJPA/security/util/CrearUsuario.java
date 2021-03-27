package com.utez.AppSecurityJPA.security.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.utez.AppSecurityJPA.security.entity.Rol;
import com.utez.AppSecurityJPA.security.entity.Usuario;
import com.utez.AppSecurityJPA.security.enums.RolNombre;
import com.utez.AppSecurityJPA.security.service.RolService;
import com.utez.AppSecurityJPA.security.service.UsuarioService;

@Component
public class CrearUsuario implements CommandLineRunner{
   @Autowired
   RolService rolService;
   
   @Autowired
   UsuarioService usuarioService;
   
   @Autowired
   PasswordEncoder passwordEncoder;

   @Override
   public void run(String... args) throws Exception {
      // TODO Auto-generated method stub
      if (rolService.getByRolNombre(RolNombre.ROLE_ADMIN) == null) {
         rolService.save(new Rol(RolNombre.ROLE_ADMIN));
      }
      
      if (rolService.getByRolNombre(RolNombre.ROLE_USER) == null) {
         rolService.save(new Rol(RolNombre.ROLE_USER));
      }
      
      if (!usuarioService.existsByEmail("adairgonzalez@localhost.com")
            || !usuarioService.existsByNickname("adair")) {
            Usuario unUsuario = new Usuario(
                  "adairgonzalez", "adair","adairgonzalez@localhost.com",
                  passwordEncoder.encode("root"));
            
            List<Rol> roles = new ArrayList<Rol>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN));
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
            unUsuario.setRoles(roles);
            
            usuarioService.save(unUsuario);
      }
      
      if (!usuarioService.existsByEmail("user@localhost.com")
            || !usuarioService.existsByNickname("user")) {
            Usuario unUsuario = new Usuario(
                  "User", "user","user@localhost.com",
                  passwordEncoder.encode("user"));
            
            List<Rol> roles = new ArrayList<Rol>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER));
            unUsuario.setRoles(roles);
            
            usuarioService.save(unUsuario);
      }
      
   }
}
