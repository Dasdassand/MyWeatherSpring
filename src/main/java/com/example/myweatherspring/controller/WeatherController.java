package com.example.myweatherspring.controller;

import com.example.myweatherspring.entity.City;
import com.example.myweatherspring.entity.TypeCondition;
import com.example.myweatherspring.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping(path = "/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService service;
    private final List<City> cities = City.getCities();

    @GetMapping
    public String getCities() {
        StringBuilder builder = new StringBuilder();
        builder.append("Список доступных городов :" + "\n");
        cities.forEach(e -> builder.append(e).append("\n"));
        return builder.toString();
    }

    @GetMapping(path = "/{city}")
    public String getWeather(@PathVariable String city) {
        var cityOpt = City.isActualCity(city, cities);
        return service.getWeather(cityOpt);
    }

}
