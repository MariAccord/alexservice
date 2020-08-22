package com.example.test_app.service;

import com.example.test_app.dto.customersearch.CustomerSearchRequest;
import com.example.test_app.dto.customersearch.CustomerSearchResponse;
import com.example.test_app.persistence.dao.tables.CustomerDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private static final int MIN_MOBILE_PHONE_LENGHT = 11;
    private final CustomerDao customerDao;

    public CustomerSearchResponse searchOrCreate(CustomerSearchRequest request){

        return null;
    }

    private List<String> getBothNumberFormats(String number){
        List<String> bothFormats = new ArrayList<>();
        bothFormats.add(number);
        if(number.length() < MIN_MOBILE_PHONE_LENGHT){
            return bothFormats;
        }
        Character c = number.charAt(0);
        if(c.equals("8")){
            bothFormats.add(new StringBuilder(number)
                    .replace(0, 1, "+7").toString());
        } else if (c.equals("+")){
            bothFormats.add(new StringBuilder(number)
                    .replace(0, 2, "8").toString());
        }
        return bothFormats;
    }
}
