package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class Card {
    private Integer id;
    private Integer userId;
    private Date startDate;
    private Date endDate;
    private Float balance;
    private Float creditLimit;


    public Card() {

    }

    public Card(
            Integer id,
            Integer userId,
            Date startDate,
            Date endDate,
            Float balance,
            Float creditLimit) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.balance = balance;
        this.creditLimit = creditLimit;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!Objects.equals(id, card.id)) return false;
        if (!Objects.equals(userId, card.userId)) return false;
        if (!Objects.equals(startDate, card.startDate)) return false;
        if (!Objects.equals(endDate, card.endDate)) return false;
        if (!Objects.equals(balance, card.balance)) return false;
        return Objects.equals(creditLimit, card.creditLimit);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (creditLimit != null ? creditLimit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card(" +
                "id=" + id +
                ", userId=" + '\'' + userId + '\'' +
                ", startDate=" + '\'' + startDate + '\'' +
                ", endDate=" + '\'' + endDate + '\'' +
                ", balance=" + '\'' + balance + '\'' +
                ", creditLimit=" + '\'' + creditLimit + '\'' +
                ')';
    }

}
