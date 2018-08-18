package com.otaviotarelho.springrestcrud.services;

import com.otaviotarelho.springrestcrud.domains.User;
import com.otaviotarelho.springrestcrud.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User findUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(new User(),"User not found"));
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
