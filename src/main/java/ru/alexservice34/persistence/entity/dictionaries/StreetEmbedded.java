package ru.alexservice34.persistence.entity.dictionaries;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class StreetEmbedded implements Serializable {

    @NotNull
    @Size(min = 2, max = 50)
    private String city;

    @NotNull
    @Size(min = 2, max = 50)
    private String street;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
