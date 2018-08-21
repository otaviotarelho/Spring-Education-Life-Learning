package edu.otaviotarelho.springrestcrud.service;

import edu.otaviotarelho.springrestcrud.builder.UserBuilder;
import edu.otaviotarelho.springrestcrud.domain.User;
import edu.otaviotarelho.springrestcrud.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTest {

    private UserRepository repository;
    private UserService service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        repository = Mockito.mock(UserRepository.class);
        service = new UserService();
        service.userRepository = repository;
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundByEmail(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findByEmail(user.getEmail())).thenThrow(ObjectNotFoundException.class);

        exception.expect(ObjectNotFoundException.class);

        service.findByEmail(user.getEmail());
    }

    @Test
    public void shouldReturnUserIfFindUserByEmail(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        service.findByEmail(user.getEmail());
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundById(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.findById(user.getId())).thenThrow(ObjectNotFoundException.class);

        exception.expect(ObjectNotFoundException.class);

        service.findById(user.getId());

    }

    @Test
    public void shouldReturnUserIfFindUserById(){
        User user = UserBuilder.oneUser().builder();
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        User userFound = service.findById(user.getId());

        Assert.assertEquals(user, userFound);
    }

    @Test
    public void shouldInsertAUser(){
        User user = UserBuilder.oneUser().builder();

        Mockito.when(repository.save(user)).thenReturn(user);
        service.insert(user);
    }

    @Test
    public void shouldNotInsertUserWithSameLogin(){

    }

    @Test
    public void shouldNotInsertUserWithAExistingEmail(){

    }

    @Test
    public void shouldNotUpdateUserIfLoginExistsAndNotTheSameUser(){

    }

    @Test
    public void shoudlNotUpdateUserIfEmailIsTheSameOfOtherUser(){

    }

    @Test
    public void shoudlDeleteUser(){

    }

}