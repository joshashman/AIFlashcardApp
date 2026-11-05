package com.josh.aitutor.services;

import com.josh.aitutor.models.User;
import com.josh.aitutor.models.Deck;
import com.josh.aitutor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    // DI constructor
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(String email){
        User newUser = new User(email);
        return userRepository.save(newUser);
    }

    public Set<Deck> getStudiedDecks(Long id){
        User user = userRepository.findById(id).orElseThrow();
        return user.getStudiedDecks();
    }
}
