package edu.otaviotarelho.springrestcrud.domain;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes="User id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 15, message = "Password should be between 8 and 15 characters")
    private String name;
    private String surname;
    @ApiModelProperty(notes="User job role / permission")
    private String jobRole;
    private LocalDate signUpDate;

    @Column(unique=true)
    @Email(message = "Invalid email")
    private String email;

    @Column(unique=true)
    @NotEmpty(message = "Login is required")
    private String login;

    @Size(min = 8, max = 15, message = "Password should be between 8 and 15 characters")
    private String password;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJobRole() {
        return jobRole;
    }
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, login, password);
    }
}
