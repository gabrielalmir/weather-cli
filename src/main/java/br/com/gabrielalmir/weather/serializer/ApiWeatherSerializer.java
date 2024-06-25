package br.com.gabrielalmir.weather.serializer;

import java.util.List;

public record ApiWeatherSerializer(
    List<WeatherSerializer> weather,
    WeatherMainSerializer main,
    WeatherWindSerializer wind,
    String name
) {}
