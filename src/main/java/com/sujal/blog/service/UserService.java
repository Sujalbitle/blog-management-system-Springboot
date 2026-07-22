package com.sujal.blog.service;

import com.sujal.blog.dto.RegisterRequest;

public interface UserService {
    String registerUser(RegisterRequest request);
}
