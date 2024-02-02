package com.example.myweatherspring.service;

import com.example.myweatherspring.entity.City;

public interface AnalysisService {
    String analysis(City city, String date);
}
