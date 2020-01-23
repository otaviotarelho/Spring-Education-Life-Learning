package edu.otaviotarelho.userservice.builder;

import edu.otaviotarelho.userservice.domain.User;

public class UserBuilder {

    private User user;

    private UserBuilder(){ }

    public static UserBuilder oneUser(){
        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        builder.user.setName("Otavio Tarelho");
        builder.user.setLogin("otaviotarelho");
        builder.user.setEmail("otaviortdb@gmail.com");
        builder.user.setPassword("teste123");

        return builder;
    }

    public User builder() {
        return user;
    }
}
