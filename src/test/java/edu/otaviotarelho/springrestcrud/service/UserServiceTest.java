package edu.otaviotarelho.springrestcrud.service;

import edu.otaviotarelho.springrestcrud.builder.UserBuilder;
import edu.otaviotarelho.springrestcrud.domain.User;
import edu.otaviotarelho.springrestcrud.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {

    private UserRepository repository;
    private UserService service;

    @BeforeEach
    void init(){
        repository = Mockito.mock(UserRepository.class);
        service = new UserService();
        service.userRepository = repository;
    }

    @Test
    void shouldThrowExceptionIfUserNotFoundByEmail(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findByEmail(user.getEmail())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.findByEmail(user.getEmail()));
    }

    @Test
    void shouldReturnUserIfFindUserByEmail(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        service.findByEmail(user.getEmail());
    }

    @Test
    void shouldThrowExceptionIfUserNotFoundById(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findById(user.getId())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.findById(user.getId()));
    }

    @Test
    void shouldReturnUserIfFindUserById(){
        User user = UserBuilder.oneUser().builder();
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        User userFound = service.findById(user.getId());

        Assertions.assertEquals(user, userFound);
    }

    @Test
    void shouldInsertAUser(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.save(user)).thenReturn(user);
        service.insert(user);
    }

}