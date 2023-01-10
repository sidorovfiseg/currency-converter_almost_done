package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
