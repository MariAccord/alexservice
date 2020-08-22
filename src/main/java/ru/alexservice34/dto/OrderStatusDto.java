package ru.alexservice34.dto;

import java.time.LocalDateTime;

public class OrderStatusDto {
    private String statusType;
    private String userName;
    private LocalDateTime dateTime;

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "OrderStatusDto{" +
                "statusType='" + statusType + '\'' +
                ", userName='" + userName + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
