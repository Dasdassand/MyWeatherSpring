package com.example.myweatherspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * DataBaseWeatherEntity
 */
@Entity
@Table(name = "weather", schema = "ds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBWE {
    @Column(name = "date_request")
    @Id
    private LocalDateTime dateRequest;
    @Column(name = "city")
    private String cityName;
    private String forecast_to_one_day;
    private String forecast_to_two_day;
    private String forecast_to_three_day;
    private String forecast_to_four_day;
    private String forecast_to_five_day;
    private String forecast_to_six_day;
    private String forecast_to_seven_day;

    public DBWE(LocalDateTime now, String name, String result) {
        dateRequest = now;
        cityName = name;
        var weathers = result.split()
        forecast_to_one_day =
    }

    public String getWeather() {
        return forecast_to_one_day + "\n" +
                forecast_to_two_day + "\n" +
                forecast_to_three_day + "\n" +
                forecast_to_four_day + "\n" +
                forecast_to_five_day + "\n" +
                forecast_to_six_day + "\n" +
                forecast_to_seven_day + "\n";
    }
}
