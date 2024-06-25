package br.com.gabrielalmir.weather.entity;

import java.util.Locale;

public class Weather {
    private final String name;
    private final String description;
    private final Double temperature;
    private final Double humidity;
    private final Double pressure;
    private final Double windSpeed;

    public Weather(String name, String description, Double temperature, Double humidity, Double pressure, Double windSpeed) {
        this.name = name;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    public String getTemperatureEmoji(double temperature) {
        if (temperature < 0.0) {
            return "❄️";
        } else if (temperature < 10.0) {
            return "🥶";
        } else if (temperature < 20.0) {
            return "😊";
        } else if (temperature < 30.0) {
            return "🌞";
        }

        return "🔥";
    }

    @Override
    public String toString() {
        var weatherText = """
        Weather in %s: %s %s
        > Temperature: %.1f°C
        > Humidity: %.1f%%
        > Pressure: %.1fhPa
        > Wind Speed: %.1fm/s""";

        var emoji = getTemperatureEmoji(temperature);

        return String.format(Locale.ENGLISH, weatherText, name, description, emoji, temperature, humidity, pressure, windSpeed);
    }
}
