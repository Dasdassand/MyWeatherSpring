package com.example.myweatherspring.repositry;

import com.example.myweatherspring.entity.City;
import com.example.myweatherspring.entity.DBWE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WeatherRepository {
    private final AbstractRepository repository;

    public void addWeather(DBWE weather) {
        try (var session = repository.getSession()) {
            session.saveOrUpdate(weather);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBWE getWeather(String date, City city) {
        return null;
    }
}
