package com.kpi.bank.system.model;

import java.util.Objects;

public class Manager {

    private Integer id;
    private Integer userId;

    public Manager() {

    }

    public Manager(
            Integer id,
            Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    protected Manager(Builder builder) {
        id = builder.id;
        userId = builder.userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static class Builder {

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
        return Objects.equals(getUserId(), manager.getUserId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manager(" +
                "id=" + id +
                ", userId=" + '\'' + userId + '\'' +
                ')';
    }
}
