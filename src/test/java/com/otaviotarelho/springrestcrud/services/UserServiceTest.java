package com.otaviotarelho.springrestcrud.services;

import com.otaviotarelho.springrestcrud.domains.User;
import com.otaviotarelho.springrestcrud.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        Mockito.when(repository.findByEmail(getUser().getEmail())).thenReturn(null);

        exception.expect(ObjectNotFoundException.class);

        service.findUserByEmail(getUser().getEmail());
    }

    @Test
    public void shouldReturnUserIfFindUserByEmail(){
        Mockito.when(repository.findByEmail(getUser().getEmail())).thenReturn(getUser());

        service.findUserByEmail(getUser().getEmail());
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundById(){
        Mockito.when(repository.findById(getUser().getId())).thenReturn(null);

        exception.expect(ObjectNotFoundException.class);

        service.findUserById(getUser().getId());

    }

    @Test
    public void shouldReturnUserIfFindUserById(){
        Mockito.when(repository.findById(getUser().getId())).thenReturn(Optional.of(getUser()));

        User user = service.findUserById(getUser().getId());

        Assert.assertEquals(getUser(), user);
    }

    @Test
    public void shouldInsertAUser(){
        User user = getUser();

        Mockito.when(repository.save(user)).thenReturn(user);
        service.insert(user);
    }

    @Test
    public void shouldNotInsertUserWithSameLogin(){

    }

    @Test
    public void shouldNotInsertUserWithAExistingEmail(){

    }

    private User getUser() {
        User user = new User();
        user.setName("Otavio Tarelho");
        user.setLogin("otaviotarelho");
        user.setEmail("otaviortdb@gmail.com");
        user.setPassword("teste123");

        return user;
    }
}