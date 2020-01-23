package edu.otaviotarelho.userservice.repository;

import edu.otaviotarelho.userservice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional(readOnly = true)
    Optional<User> findByEmail(String email);
}
