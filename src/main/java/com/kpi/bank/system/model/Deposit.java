package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class Deposit {
    private Integer id;
    private Integer cardId;
    private Date startDate;
    private Date endDate;
    private Float percent;
    private Integer periodDays;
    private Float price;

    public Deposit() {

    }

    public Deposit(
            Integer id,
            Integer cardId,
            Date startDate,
            Date endDate,
            Float percent,
            Integer periodDays,
            Float price) {
        this.id = id;
        this.cardId = cardId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percent = percent;
        this.periodDays = periodDays;
        this.price = price;
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

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    public Integer getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(Integer periodDays) {
        this.periodDays = periodDays;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (!Objects.equals(id, deposit.id)) return false;
        if (!Objects.equals(cardId, deposit.cardId)) return false;
        if (!Objects.equals(startDate, deposit.startDate)) return false;
        if (!Objects.equals(endDate, deposit.endDate)) return false;
        if (!Objects.equals(percent, deposit.percent)) return false;
        if (!Objects.equals(periodDays, deposit.periodDays)) return false;
        return Objects.equals(price, deposit.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (periodDays != null ? periodDays.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Deposit(" +
                "id=" + id +
                ", cardId=" + '\'' + cardId + '\'' +
                ", startDate=" + '\'' + startDate + '\'' +
                ", endDate=" + '\'' + endDate + '\'' +
                ", percent=" + '\'' + percent + '\'' +
                ", periodDays=" + '\'' + periodDays + '\'' +
                ", price=" + '\'' + price + '\'' +
                ')';
    }

}
