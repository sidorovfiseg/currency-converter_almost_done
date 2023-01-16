package com.example.currencyconverter.servicies;

import com.example.currencyconverter.entities.Role;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserRequest;
import com.example.currencyconverter.repositories.RoleRepo;
import com.example.currencyconverter.repositories.UserRepo;
import com.example.currencyconverter.repositories.UserRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/*
Логика для работы с пользователеми
тут тоже говорящие методы
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRequestRepo userRequestRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username mot found");
        }

        return user;
    }


    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public ArrayList<String> getAllUserNames() {
        List<User> users = allUsers();
        ArrayList<String> names = new ArrayList<>();
        for(User user: users) {
            names.add(user.getUsername());
        }

        return names;
    }

    public List<User> getAllUsersExceptAdminAndManager() {
        List<User> users = allUsers();
        Iterator<User> i = users.iterator();

        while (i.hasNext()) {
            User user = i.next();
            for(Role role: user.getRoles()) {
                if(role.getName().equals("ROLE_ADMIN") || role.getName().equals("ROLE_MANAGER")) {
                    i.remove();
                }
            }
        }

        return users;

    }


    public User getUserByName(String name) {
        return userRepo.findByUsername(name);
    }

    public boolean deleteUser(String userName) {

        User user =userRepo.findByUsername(userName);

        if (user.isEnabled()) {
            //userRequestRepo.deleteAllByUserId(user.getId());
            userRepo.deleteById(user.getId());


            return true;
        }
        return false;
    }

}
