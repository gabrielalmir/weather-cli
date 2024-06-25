package br.com.gabrielalmir.weather.serializer;

public record WeatherMainSerializer(
    Double temp,
    Double humidity,
    Double pressure
) {}
