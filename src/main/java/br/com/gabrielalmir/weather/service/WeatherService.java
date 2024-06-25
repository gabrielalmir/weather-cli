package br.com.gabrielalmir.weather.service;

import br.com.gabrielalmir.weather.entity.Weather;
import br.com.gabrielalmir.weather.serializer.ApiWeatherSerializer;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class WeatherService {
    private final Gson gson;
    private final OkHttpClient httpClient;
    private final String apiKey;

    public WeatherService(Gson gson, OkHttpClient httpClient, String apiKey) {
        this.gson = gson;
        this.httpClient = httpClient;
        this.apiKey = apiKey;
    }

    public Weather getWeather(String city, String country) throws IOException {
        var weatherResponse = getWeatherResponse(city, country);

        var name = weatherResponse.name();
        var description = weatherResponse.weather().getFirst().description();
        var temperature = weatherResponse.main().temp();
        var humidity = weatherResponse.main().humidity();
        var pressure = weatherResponse.main().pressure();
        var wind = weatherResponse.wind().speed();

        return new Weather(name, description, temperature, humidity, pressure, wind);
    }

    ApiWeatherSerializer getWeatherResponse(String city, String country) throws IOException {
        var url = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&appid=%s".formatted(city, country, apiKey);
        var request = new Request.Builder().url(url).build();
        okhttp3.ResponseBody body;

        try (var response = httpClient.newCall(request).execute()) {
            body = response.body();
        }

        assert body != null;
        return gson.fromJson(body.string(), ApiWeatherSerializer.class);
    }
}
