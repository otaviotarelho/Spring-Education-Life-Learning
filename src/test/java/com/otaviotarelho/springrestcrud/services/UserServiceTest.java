package com.otaviotarelho.springrestcrud.services;

import com.otaviotarelho.springrestcrud.domains.User;
import com.otaviotarelho.springrestcrud.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class UserServiceTest {

    private UserRepository repository;
    private UserService service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        repository = Mockito.mock(UserRepository.class);
        service = new UserService();
    }

    @Test
    public void shouldThrowExceptionIfUserNotFound(){
        Mockito.when(repository.findByEmail(getUser().getEmail())).thenReturn(null);

        exception.expect(ObjectNotFoundException.class);
        exception.expectMessage("User not found");
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