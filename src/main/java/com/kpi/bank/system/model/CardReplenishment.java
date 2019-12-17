package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class CardReplenishment {

    private Integer id;
    private Integer transactionId;
    private String cardNumber;

    public CardReplenishment() {

    }

    public CardReplenishment(
            Integer id,
            Integer transactionId,
            String cardNumber) {
        this.id = id;
        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
    }

    protected CardReplenishment(Builder builder) {
        id = builder.id;
        transactionId = builder.transactionId;
        cardNumber = builder.cardNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public static class Builder {

        private Integer id;
        private Integer transactionId;
        private String cardNumber;


        public Builder() {

        }

        public Builder setId(Integer id) {
            this.id = id;

            return this;
        }

        public Builder setTransactionId(Integer transactionId) {
            this.transactionId = transactionId;

            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;

            return this;
        }


        public CardReplenishment build() {
            return new CardReplenishment(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardReplenishment cardReplenishment = (CardReplenishment) o;

        if (!Objects.equals(id, cardReplenishment.id)) return false;
        if (!Objects.equals(transactionId, cardReplenishment.transactionId)) return false;
        return Objects.equals(cardNumber, cardReplenishment.cardNumber);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardReplenishment(" +
                "id=" + id +
                ", transactionId=" + '\'' + transactionId + '\'' +
                ", cardNumber=" + '\'' + cardNumber + '\'' +
                ')';
    }

}
