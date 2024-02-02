package com.example.myweatherspring.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dasdassand
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;

    public static List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Москва", 1220988, "55.7522", "37.6156"));
        cities.add(new City("Санкт-Петербург", 536203, "59.9386", "30.3141"));
        cities.add(new City("Воронеж", 472045, "51.672", "39.1843"));
        cities.add(new City("Липецк", 535121, "52.6031", "39.5708"));
        cities.add(new City("Казань", 551487, "55.7887", "49.1221"));
        return cities;
    }

    public static City isActualCity(String city, List<City> cities) {
        AtomicReference<City> cityOpt = new AtomicReference<>(null);
        cities.forEach(cityList -> {
            if (cityList.getName().equals(city))
                cityOpt.set(cityList);
        });
        if (cityOpt.get() == null) {
            throw new NoSuchElementException();
        }
        return cityOpt.get();
    }
}
