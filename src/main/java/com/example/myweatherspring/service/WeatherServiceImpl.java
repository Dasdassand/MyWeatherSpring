package com.example.myweatherspring.service;

import com.example.myweatherspring.entity.City;
import com.example.myweatherspring.entity.DBWE;
import com.example.myweatherspring.entity.TypeCondition;
import com.example.myweatherspring.repositry.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final TypeCondition condition;
    private final WeatherRepository repository;

    /**
     * @param city - город
     * @return res - строка с описанием погоды на неделю
     * @throws IOException
     */
    private String getYandex(City city) throws IOException {
        var weathers = parserYandex(city);
        StringBuilder res = new StringBuilder();
        for (String s : weathers) {
            res.append(s).append("\n").append("\n");
        }
        return res.toString();
    }

    /**
     * @param city - город
     * @return reader - ответ с api yandex в формате json
     * @throws IOException
     */
    private BufferedReader getBufferedReaderYandex(City city) throws IOException {
        String request = "https://api.weather.yandex.ru/v2/forecast?lat=" + city.getLat() + "&lon=" +
                city.getLon() + "&lang=Ru-ru&limit=7&hours=false&extra=false";
        URL url = new URL(request);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("X-Yandex-API-Key", "e8dc4242-1276-45bb-afdd-4c0c5ac10142");
        return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
    }

    /**
     * @param city - Город
     * @return res - выдержка из json с основной информацией неедльной температурой
     * @throws IOException
     */
    private String[] parserYandex(City city) throws IOException {
        BufferedReader reader = getBufferedReaderYandex(city);
        String line;
        boolean flag = false;
        String tmp = "";
        String[] res = new String[7];
        StringBuilder response = new StringBuilder();
        int count = 0;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        System.out.println(response);
        reader.close();
        int index = 0;
        String[] resp = response.toString().split(",");

        for (String lineS :
                resp) {
            if (lineS.contains("date") && !lineS.contains("date_ts")) {
                tmp += "Дата : ";
                tmp += count == 0
                        ? lineS.split(":")[2].replaceAll("\"", "").replaceAll(" ", "")
                        : lineS.split(":")[1].replaceAll("\"", "").replaceAll(" ", "");
                count = 1;
                tmp += "\n";
            }
            if (lineS.contains("\"day\""))
                flag = true;
            if (lineS.contains("temp_min") && flag)
                tmp += "Минимальная температура : " + lineS.split(":")[1] + ("°") + ("\n");
            if (lineS.contains("temp_max") && flag)
                tmp += "Максимальная температура : " + lineS.split(":")[1] + ("°") + ("\n");
            if (lineS.contains("wind_speed") && flag)
                tmp += "Скорость ветра - " + lineS.split(":")[1] + (" м/с") + ("\n");
            if (lineS.contains("condition") && flag) {
                tmp += "Состояние - " +
                        condition.getConditions(lineS.split(":")[1]);
                res[index] = tmp;
                index++;
                flag = false;
                tmp = "";
            }

        }
        return res;
    }

    @Override
    public String getWeather(City city) {
        String result = "";
        try {
            result = getYandex(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.addWeather(new DBWE(LocalDateTime.now(),city.getName(),result));
        return result;
    }
}
