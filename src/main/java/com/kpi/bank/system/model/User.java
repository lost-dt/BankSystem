package com.kpi.bank.system.model;

import java.util.Objects;

public class User {

    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String secretWord;

    public User(
            Integer id,
            String email,
            String password,
            String firstName,
            String lastName,
            String secretWord) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secretWord = secretWord;
    }

    protected User(Builder<?> builder) {
        id = builder.id;
        email = builder.email;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
        secretWord = builder.secretWord;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }


    public static class Builder<T extends Builder<T>> {

        private Integer id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String secretWord;

        public Builder() {

        }

        public T setId(Integer id) {
            this.id = id;

            return (T) this;
        }

        public T setEmail(String email) {
            this.email = email;

            return (T) this;
        }

        public T setPassword(String password) {
            this.password = password;

            return (T) this;
        }

        public T setFirstName(String firstName) {
            this.firstName = firstName;

            return (T) this;
        }

        public T setLastName(String lastName) {
            this.lastName = lastName;

            return (T) this;
        }

        public T setSecretWord(String secretWord) {
            this.secretWord = secretWord;

            return (T) this;
        }

        public User build() {
            return new User(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        return Objects.equals(secretWord, user.secretWord);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (secretWord != null ? secretWord.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User(" +
                "id=" + id +
                ", email=" + '\'' + email + '\'' +
                ", password=" + '\'' + password + '\'' +
                ", firstName=" + '\'' + firstName + '\'' +
                ", lastName=" + '\'' + lastName + '\'' +
                ", secretWord=" + '\'' + secretWord + '\'' +
                ')';
    }

}
