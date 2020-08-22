package ru.alexservice34.dto.customersearch;

import lombok.Data;

@Data
public class CustomerSearchRequest {
    private String number;
    private Address address;
    private String comment;
    private String contactTypeCode;

    @Data
    public static class Address{
        private String city;
        private String street;
        private String buildingNumber;
        private Integer flatNumber;
        private String office;
    }
}
