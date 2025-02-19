package com.boots.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boots.security.dto.UserDto;
import com.boots.security.entity.RoleEntity;
import com.boots.security.entity.UserEntity;
import com.boots.security.repository.RoleRepository;
import com.boots.security.repository.UserRepository;
import com.boots.security.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//UserDetailsService (необходим для Spring Security), в котором нужно переопределить один метод loadUserByUsername().
@Service
public class UserServiceIml implements UserService, UserDetailsService {
    //В Java, аннотация @PersistenceContext используется в 
    //приложениях, работающих с JPA (Java Persistence API), и 
    //позволяет внедрять (inject) экземпляр 
    //EntityManager в ваш сервисный класс. Давайте разберем, зачем это нужно и как оно работает.
    @PersistenceContext
    //EntityManager в JPA предоставляет базовые CRUD-операции для работы с сущностями любого вида
    private EntityManager em;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    //BCryptPasswordEncoder — это класс из Spring Security, который используется для хеширования паролей с использованием алгоритма BCrypt.
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceIml(UserRepository userRepository, RoleRepository roleRepository,
    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        return user;
    }

    @Override
    public UserEntity findUserById(Long userId) {
        Optional<UserEntity> userFormDb = userRepository.findById(userId);
        return userFormDb.orElse(new UserEntity());
    }



    @Override
    public List<UserEntity> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean saveUser(UserEntity user) {
        UserEntity userFormDb = userRepository.findByUsername(user.getUsername());

        if(userFormDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
        //хешируем пароль через encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto createUser(UserEntity user) {
        UserEntity userEntity = this.userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setUsername(userEntity.getUsername());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRoles(userEntity.getRoles());
        return userDto;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if(userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> usergtList(Long idMin) {
        return em.createQuery(
            "SELECT u FROM user u WHERE u.id > :paramId", 
            UserEntity.class
        )
            .setParameter("paramId", idMin).getResultList();
    }
}
