package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRequestRepo extends JpaRepository<UserRequest, Long> {

    List<UserRequest> findAllByUserId(Long id);
    UserRequest findUserRequestById(Long id);

    void deleteAllByUserId(Long id);
}
