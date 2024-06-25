package br.com.gabrielalmir;

import br.com.gabrielalmir.weather.service.WeatherService;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var gson = new Gson();
        var httpClient = new OkHttpClient();
        var weatherService = new WeatherService(gson, httpClient, System.getenv("OPENWEATHER_API_KEY"));
        var sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter your city: ");
            var city = sc.nextLine().trim();

            System.out.println("Please enter your country code: (e.g. US, UK, IN, BR)");
            var country = sc.nextLine().trim();

            if (country.isEmpty() || city.isEmpty()) {
                System.out.println("Invalid input");
                continue;
            }

            // show weather information
            System.out.printf("Looking for weather of %s - %s\n", city, country);

            try {
                var weather = weatherService.getWeather(city, country);
                System.out.println(weather);
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again");
                continue;
            }

            // ask if needs to continue
            System.out.println("Do you want to check another city? (y/n) ");
            var answer = sc.nextLine().trim().toLowerCase();

            if (!answer.equals("y") && !answer.equals("yes")) {
                System.out.println("Goodbye! 😊");
                break;
            }

            System.out.println("*".repeat(20));
        }
    }
}