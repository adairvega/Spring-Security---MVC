package com.utez.AppSecurityJPA.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utez.AppSecurityJPA.security.entity.Rol;
import com.utez.AppSecurityJPA.security.enums.RolNombre;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer>{
   
   Rol findByRolNombre(RolNombre rolNombre);
   
}
