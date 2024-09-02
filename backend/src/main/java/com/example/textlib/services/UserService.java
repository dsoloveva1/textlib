package com.example.textlib.services;

import com.example.textlib.models.User;
import com.example.textlib.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User savedUser = userRepository.findByEmail(user.getEmail());

        if (savedUser != null) {
            throw new RuntimeException("Пользователь уже зарегистрирован");
        }

        savedUser.setRole("ROLE_USER");
        savedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.createUser(user);
        return savedUser;
    }


}
