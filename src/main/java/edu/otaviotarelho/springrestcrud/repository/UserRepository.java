package edu.otaviotarelho.springrestcrud.repository;

import edu.otaviotarelho.springrestcrud.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional(readOnly = true)
    Optional<User> findByEmail(String email);
}
