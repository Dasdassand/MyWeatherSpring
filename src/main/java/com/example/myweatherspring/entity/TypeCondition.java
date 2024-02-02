package com.example.myweatherspring.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Dasdassand
 */
@Component
public class TypeCondition {
    private final HashMap<String, String> conditions = new HashMap<>();

    {
        conditions.put("\"clear\"", "ясно");
        conditions.put("\"partly-cloudy\"", "малооблачно");
        conditions.put("\"cloudy\"", "облачно с прояснениями");
        conditions.put("\"overcast\"", "пасмурно");
        conditions.put("\"light-rain\"", "небольшой дождь");
        conditions.put("\"rain\"", "дождь");
        conditions.put("\"heavy-rain\"", "сильный дождь");
        conditions.put("\"showers\"", "ливень");
        conditions.put("\"wet-snow\"", "дождь со снегом");
        conditions.put("\"light-snow\"", "небольшой снег");
        conditions.put("\"snow\"", "снег");
        conditions.put("\"snow-showers\"", "снегопад");
        conditions.put("\"hail\"", "град");
        conditions.put("\"thunderstorm\"", "гроза");
        conditions.put("\"thunderstorm-with-rain\"", "дождь с грозой");
        conditions.put("\" thunderstorm-with-hail\"", "гроза с градом");
    }

    public TypeCondition getTypeCondition() {
        return new TypeCondition();
    }

    public String getConditions(String cond) {
        return conditions.get(cond);
    }
}
