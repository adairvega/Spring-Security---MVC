package com.utez.AppSecurityJPA.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utez.AppSecurityJPA.security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
   
   Usuario findByNickname(String nickname);
   boolean existsByNickname(String nickname);
   boolean existsByemail(String email);
}
