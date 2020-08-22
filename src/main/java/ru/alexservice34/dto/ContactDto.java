package ru.alexservice34.dto;

public class ContactDto {
    private String number;
    private String contactType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                ", number='" + number + '\'' +
                ", contactType='" + contactType + '\'' +
                '}';
    }
}
