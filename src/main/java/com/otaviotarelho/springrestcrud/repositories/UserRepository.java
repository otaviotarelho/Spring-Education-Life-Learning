package com.otaviotarelho.springrestcrud.repositories;

import com.otaviotarelho.springrestcrud.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
