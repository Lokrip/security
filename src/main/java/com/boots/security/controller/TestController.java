package com.boots.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boots.security.entity.UserEntity;
import com.boots.security.repository.UserRepository;


@Controller
public class TestController {

    private final UserRepository userRepository;
    @Autowired
    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public String test(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("user", userEntity.getUsername());
        return "index.html";
    }
}
