package com.kpi.bank.system.model;

import java.util.Objects;

public class Manager extends User {

    private Integer id;
    private Integer userId;

    public Manager(
            Integer id,
            Integer userId,
            String email,
            String password,
            String firstName,
            String lastName,
            String secretWord) {
        super(userId, email, password, firstName, lastName, secretWord);
        this.id = id;
        this.userId = userId;
    }

    protected Manager(Builder builder) {
        super(builder);
        id = builder.id;
        userId = builder.userId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static class Builder extends User.Builder<Builder> {

        private Integer id;
        private Integer userId;


        public Builder() {

        }

        public Builder setId(Integer id) {
            this.id = id;

            return this;
        }

        public Builder setUserId(Integer userId) {
            this.userId = userId;

            return this;
        }

        public Manager build() {
            return new Manager(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (!Objects.equals(getId(), manager.getId())) return false;
        if (!Objects.equals(getUserId(), manager.getUserId())) return false;
        if (!Objects.equals(getEmail(), manager.getEmail())) return false;
        if (!Objects.equals(getPassword(), manager.getPassword())) return false;
        if (!Objects.equals(getFirstName(), manager.getFirstName())) return false;
        if (!Objects.equals(getLastName(), manager.getLastName())) return false;
        return Objects.equals(getSecretWord(), manager.getSecretWord());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getSecretWord() != null ? getSecretWord().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manager(" +
                "id=" + id +
                ", userId=" + '\'' + userId + '\'' +
                ", email=" + '\'' + getEmail() + '\'' +
                ", password=" + '\'' + getPassword() + '\'' +
                ", firstName=" + '\'' + getFirstName() + '\'' +
                ", lastName=" + '\'' + getLastName() + '\'' +
                ", secretWord=" + '\'' + getSecretWord() + '\'' +
                ')';
    }
}
