package com.example.myweatherspring.controller;

import com.example.myweatherspring.entity.City;
import com.example.myweatherspring.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/weather")
@RequiredArgsConstructor
public class AnalysisController {
    private final List<City> cities = City.getCities();
    private final AnalysisService service;

    @GetMapping("/analysis/{city}&{date}")
    public String getAnalysis(@PathVariable String city, @PathVariable String date) {
        var cityOpt = City.isActualCity(city, cities);
        return service.analysis(cityOpt, date);
    }


}
