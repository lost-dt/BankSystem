package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class PhoneReplenishment extends Transaction {

    private Integer id;
    private Integer transactionId;
    private String phoneNumber;

    public PhoneReplenishment(
            Integer id,
            Integer transactionId,
            Integer cardId,
            Float price,
            Date startTime,
            Date endTime,
            String phoneNumber) {
        super(transactionId, cardId, price, startTime, endTime);
        this.id = id;
        this.transactionId = transactionId;
        this.phoneNumber = phoneNumber;
    }

    protected PhoneReplenishment(Builder builder) {
        super(builder);
        id = builder.id;
        transactionId = builder.transactionId;
        phoneNumber = builder.phoneNumber;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class Builder extends Transaction.Builder<Builder> {

        private Integer id;
        private Integer transactionId;
        private String phoneNumber;


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

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }


        public PhoneReplenishment build() {
            return new PhoneReplenishment(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneReplenishment phoneReplenishment = (PhoneReplenishment) o;

        if (!Objects.equals(id, phoneReplenishment.id)) return false;
        if (!Objects.equals(transactionId, phoneReplenishment.transactionId)) return false;
        if (!Objects.equals(phoneNumber, phoneReplenishment.phoneNumber)) return false;
        if (!Objects.equals(getCardId(), phoneReplenishment.getCardId())) return false;
        if (!Objects.equals(getPrice(), phoneReplenishment.getPrice())) return false;
        if (!Objects.equals(getStartTime(), phoneReplenishment.getStartTime())) return false;
        return Objects.equals(getEndTime(), phoneReplenishment.getEndTime());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (getCardId() != null ? getCardId().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneReplenishment(" +
                "id=" + id +
                ", transactionId=" + '\'' + transactionId + '\'' +
                ", phoneNumber=" + '\'' + phoneNumber + '\'' +
                ", cardId=" + '\'' + getCardId() + '\'' +
                ", price=" + '\'' + getPrice() + '\'' +
                ", startTime=" + '\'' + getStartTime() + '\'' +
                ", endTime=" + '\'' + getEndTime() + '\'' +
                ')';
    }

}
