package com.example.myweatherspring.service;

import com.example.myweatherspring.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    @Override
    public String analysis(City city, String date) {
        String cityName = "";
        for (City cityC :
                City.getCities()) {
            if (cityC.getName().equals(city)) {
                cityName = cityC.getName();
                break;
            }
        }
        var data = getData(date, cityName).split("\n");
        var countRecord = data.length / 5;
        int indexTempMin = 2, indexTempMax = 1, maxT, minT;
        class Temp {
            int minTemp;
            int maxTemp;

            public Temp(int minTemp, int maxTemp) {
                this.minTemp = minTemp;
                this.maxTemp = maxTemp;
            }

            public boolean equals(Temp temp) {
                return (this.maxTemp == temp.maxTemp) && (this.minTemp == temp.minTemp);
            }
        }
        List<Temp> temps = new ArrayList<>();

        for (int i = 0; i < countRecord; i++) {
            maxT = Integer.parseInt(data[indexTempMax]
                    .split(":")[1].replaceAll("°", "")
                    .replaceAll(" ", ""));
            minT = Integer.parseInt(data[indexTempMin]
                    .split(":")[1].replaceAll("°", "")
                    .replaceAll(" ", ""));
            indexTempMax += 5;
            indexTempMin += 5;
            temps.add(new Temp(minT, maxT));
        }
        int tMaxDif = 0, tMinDif = 0;
        boolean flagWhile;
        do {
            flagWhile = false;
            if (temps.size() == 0) {
                break;
            }
            var t = temps.get(0);
            for (int j = 1; j < temps.size(); j++) {
                if (!t.equals(temps.get(j))) {
                    flagWhile = true;
                    var i = temps.get(j);
                    temps.remove(j);
                    if (t.maxTemp != i.maxTemp && (t.maxTemp - i.maxTemp) > tMaxDif) {
                        tMaxDif = t.maxTemp - i.maxTemp;
                    }
                    if (t.minTemp != i.minTemp && (t.minTemp - i.minTemp) > tMinDif) {
                        tMinDif = t.minTemp - i.minTemp;
                    }
                }
            }
        } while (flagWhile);

        if (tMaxDif == 0 && tMinDif == 0) {
            return "В течение дня средняя темперура не изменялась";
        } else {
            return "В течение дня измениение максимальной температуры составило - " + tMaxDif + "°"
                    + ", а минимальной - " + tMinDif + "°.";
        }
    }

}
