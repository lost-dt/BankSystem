package com.kpi.bank.system.model;

import java.util.Date;
import java.util.Objects;

public class Credit {
    private Integer id;
    private Integer cardId;
    private Integer managerId;
    private Date startDate;
    private Date endDate;
    private Float percent;
    private Integer periodDays;
    private Float price;

    public Credit() {

    }

    public Credit(
            Integer id,
            Integer cardId,
            Integer managerId,
            Date startDate,
            Date endDate,
            Float percent,
            Integer periodDays,
            Float price) {
        this.id = id;
        this.cardId = cardId;
        this.managerId = managerId;
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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

        Credit credit = (Credit) o;

        if (!Objects.equals(id, credit.id)) return false;
        if (!Objects.equals(cardId, credit.cardId)) return false;
        if (!Objects.equals(managerId, credit.managerId)) return false;
        if (!Objects.equals(startDate, credit.startDate)) return false;
        if (!Objects.equals(endDate, credit.endDate)) return false;
        if (!Objects.equals(percent, credit.percent)) return false;
        if (!Objects.equals(periodDays, credit.periodDays)) return false;
        return Objects.equals(price, credit.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (periodDays != null ? periodDays.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit(" +
                "id=" + id +
                ", cardId=" + '\'' + cardId + '\'' +
                ", managerId=" + '\'' + managerId + '\'' +
                ", startDate=" + '\'' + startDate + '\'' +
                ", endDate=" + '\'' + endDate + '\'' +
                ", percent=" + '\'' + percent + '\'' +
                ", periodDays=" + '\'' + periodDays + '\'' +
                ", price=" + '\'' + price + '\'' +
                ')';
    }

}
