package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class Transaction {

    private Integer id;
    private Integer cardId;
    private Float price;
    private Date startTime;
    private Date endTime;

    public Transaction(
            Integer id,
            Integer cardId,
            Float price,
            Date startTime,
            Date endTime) {
        this.id = id;
        this.cardId = cardId;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected Transaction(Builder<?> builder) {
        id = builder.id;
        cardId = builder.cardId;
        price = builder.price;
        startTime = builder.startTime;
        endTime = builder.endTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public static class Builder<T extends Builder<T>> {

        private Integer id;
        private Integer cardId;
        private Float price;
        private Date startTime;
        private Date endTime;

        public Builder() {

        }

        public T setId(Integer id) {
            this.id = id;

            return (T) this;
        }

        public T setCardId(Integer cardId) {
            this.cardId = cardId;

            return (T) this;
        }

        public T setPrice(Float price) {
            this.price = price;

            return (T) this;
        }

        public T setStartTime(Date startTime) {
            this.startTime = startTime;

            return (T) this;
        }

        public T setEndTime(Date endTime) {
            this.endTime = endTime;

            return (T) this;
        }

        public Transaction build() {
            return new Transaction(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction transaction = (Transaction) o;

        if (!Objects.equals(id, transaction.id)) return false;
        if (!Objects.equals(cardId, transaction.cardId)) return false;
        if (!Objects.equals(startTime, transaction.startTime)) return false;
        if (!Objects.equals(endTime, transaction.endTime)) return false;
        return Objects.equals(price, transaction.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction(" +
                "id=" + id +
                ", cardId=" + '\'' + cardId + '\'' +
                ", startTime=" + '\'' + startTime + '\'' +
                ", endTime=" + '\'' + endTime + '\'' +
                ", price=" + '\'' + price + '\'' +
                ')';
    }

}
