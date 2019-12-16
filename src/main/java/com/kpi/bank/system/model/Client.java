package com.kpi.bank.system.model;

import java.util.Objects;

public class Client extends User {

    private Integer id;
    private Integer userId;
    private Integer lifePeriod;

    public Client(
            Integer id,
            Integer userId,
            String email,
            String password,
            String firstName,
            String lastName,
            String secretWord,
            Integer lifePeriod) {
        super(userId, email, password, firstName, lastName, secretWord);
        this.id = id;
        this.userId = userId;
        this.lifePeriod = lifePeriod;
    }

    protected Client(Builder builder) {
        super(builder);
        id = builder.id;
        userId = builder.userId;
        lifePeriod = builder.lifePeriod;
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

    public Integer getFilePeriod() {
        return lifePeriod;
    }

    public void setFilePeriod(Integer filePeriod) {
        this.lifePeriod = filePeriod;
    }

    public static class Builder extends User.Builder<Builder> {

        private Integer id;
        private Integer userId;
        private Integer lifePeriod;


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

        public Builder lifePeriod(Integer lifePeriod) {
            this.lifePeriod = lifePeriod;

            return this;
        }

        public Client build() {
            return new Client(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(getId(), client.getId())) return false;
        if (!Objects.equals(getUserId(), client.getUserId())) return false;
        if (!Objects.equals(getEmail(), client.getEmail())) return false;
        if (!Objects.equals(getPassword(), client.getPassword())) return false;
        if (!Objects.equals(getFirstName(), client.getFirstName())) return false;
        if (!Objects.equals(getLastName(), client.getLastName())) return false;
        if (!Objects.equals(getSecretWord(), client.getSecretWord())) return false;
        return Objects.equals(getFilePeriod(), client.getFilePeriod());
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
        result = 31 * result + (lifePeriod != null ? lifePeriod.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client(" +
                "id=" + id +
                ", userId=" + '\'' + userId + '\'' +
                ", email=" + '\'' + getEmail() + '\'' +
                ", password=" + '\'' + getPassword() + '\'' +
                ", firstName=" + '\'' + getFirstName() + '\'' +
                ", lastName=" + '\'' + getLastName() + '\'' +
                ", secretWord=" + '\'' + getSecretWord() + '\'' +
                ", lifePeriod=" + '\'' + lifePeriod + '\'' +
                ')';
    }

}
