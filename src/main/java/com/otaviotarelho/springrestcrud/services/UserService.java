package com.otaviotarelho.springrestcrud.services;

import com.otaviotarelho.springrestcrud.domains.User;
import com.otaviotarelho.springrestcrud.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User findUserById(Integer id){
        Optional<User> user = userRepository.findById(id);

        if(user == null){
            throw new ObjectNotFoundException(new User(), "User not found");
        }

        return user.get();
    }

    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new ObjectNotFoundException(new User(), "User not found");
        }

        return user;
    }

    public User insert(User obj){
        obj.setId(null);
        return userRepository.save(obj);
    }

    public User update(User obj){
        return userRepository.save(obj);
    }

    public void delete(Integer id){
        User user = findUserById(id);
        userRepository.delete(user);
    }
}
