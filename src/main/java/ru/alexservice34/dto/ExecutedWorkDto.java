package ru.alexservice34.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExecutedWorkDto {
    private String userName;
    private String workName;
    private BigDecimal cost;
    private LocalDateTime dateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ExecutedWorkDto{" +
                "userName='" + userName + '\'' +
                ", workName='" + workName + '\'' +
                ", cost=" + cost +
                ", dateTime=" + dateTime +
                '}';
    }
}
