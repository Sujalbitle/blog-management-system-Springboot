package com.sujal.blog.service.impl;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.sujal.blog.dto.RegisterRequest;
import com.sujal.blog.repository.UserRepository;
import com.sujal.blog.service.UserService;
import org.springframework.stereotype.Service;
import com.sujal.blog.entity.Role;
import com.sujal.blog.entity.User;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public String registerUser(RegisterRequest request){
    if(userRepository.existsByEmail(request.getEmail())){
        return "Email already exists!";
    }
    User user=User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .createdAt(LocalDateTime.now())
            .build();
    userRepository.save(user);
    return "User Registered Successfully!";
    }
}
