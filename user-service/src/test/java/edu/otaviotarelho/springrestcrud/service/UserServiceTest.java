package edu.otaviotarelho.springrestcrud.service;

import edu.otaviotarelho.springrestcrud.builder.UserBuilder;
import edu.otaviotarelho.springrestcrud.domain.User;
import edu.otaviotarelho.springrestcrud.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldThrowExceptionIfUserNotFoundByEmail(){
        User user = UserBuilder.oneUser().builder();

        when(repository.findByEmail(anyString())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.findByEmail(user.getEmail()));
    }

    @Test
    void shouldReturnUserIfFindUserByEmail(){
        User user = UserBuilder.oneUser().builder();

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));

        service.findByEmail(user.getEmail());
    }

    @Test
    void shouldThrowExceptionIfUserNotFoundById(){
        User user = UserBuilder.oneUser().builder();

        when(repository.findById(anyInt())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.findById(user.getId()));
    }

    @Test
    void shouldReturnUserIfFindUserById(){
        User user = UserBuilder.oneUser().builder();
        when(repository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        User userFound = service.findById(user.getId());

        Assertions.assertEquals(user, userFound);
    }

    @Test
    void shouldInsertAUser(){
        User user = UserBuilder.oneUser().builder();

        when(repository.save(any(User.class))).thenReturn(user);
        service.insert(user);
    }

    @Test
    void shouldReturnAListOfUsers(){
        when(repository.findAll()).thenReturn(Arrays.asList(
                UserBuilder.oneUser().builder(),
                UserBuilder.oneUser().builder()
        ));

        service.findAll();
    }
}