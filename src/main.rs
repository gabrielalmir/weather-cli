use dotenvy::dotenv;
use colored::*;
use serde::Deserialize;
use std::{env, io};

#[derive(Deserialize, Debug)]
struct WeatherResponse {
    weather: Vec<Weather>,
    main: WeatherMain,
    wind: WeatherWind,
    name: String,
}

#[derive(Deserialize, Debug)]
struct Weather {
    description: String,
}

#[derive(Deserialize, Debug)]
struct WeatherMain {
    temp: f64,
    humidity: f64,
    pressure: f64,
}

#[derive(Deserialize, Debug)]
struct WeatherWind {
    speed: f64,
}

fn main() {
    dotenv().expect("Failed to read .env file");
    println!("Weather CLI");

    loop {
        println!("{}", "Please enter the city:".green());
        let mut city = String::new();
        io::stdin().read_line(&mut city).expect("Failed to read line");
        let city = city.trim();

        println!("{}", "Please enter the country code: (e.g. US, UK, IN, BR)".green());
        let mut country = String::new();
        io::stdin().read_line(&mut country).expect("Failed to read line");
        let country = country.trim();

        let api_key = env::var("WEATHER_API_TOKEN").expect("WEATHER_API_TOKEN is not set");

        match get_weather(city, country, api_key.as_str()) {
            Ok(response) => display_weather(&response),
            Err(error) => println!("Error: {}", error),
        }

        println!("{}", "Do you want to check another city? (y/n)".green());
        let mut answer = String::new();
        io::stdin().read_line(&mut answer).expect("Failed to read line");
        let answer = answer.trim().to_lowercase();

        if answer != "yes" && answer != "y" {
            println!("{}", "Goodbye! 😊".green());
            break;
        }
    }
}

fn get_weather(
    city: &str,
    country: &str,
    api_key: &str,
) -> Result<WeatherResponse, reqwest::Error> {
    let url = format!(
        "http://api.openweathermap.org/data/2.5/weather?q={},{}&units=metric&appid={}",
        city, country, api_key
    );
    let response = reqwest::blocking::get(&url)?.json::<WeatherResponse>()?;
    Ok(response)
}

fn display_weather(weather: &WeatherResponse) {
    let description = &weather.weather[0].description;
    let temp = weather.main.temp;
    let humidity = weather.main.humidity;
    let pressure = weather.main.pressure;
    let wind_speed = weather.wind.speed;

    let weather_text = format!(
        "Weather in {}: {} {}
> Temperature: {:.1}°C
> Humidity: {:.1}%
> Pressure: {:.1}hPa
> Wind Speed: {:.1}m/s",
        weather.name, description, get_temp_emoji(temp), temp, humidity, pressure, wind_speed
    );

    let weather_text_color = match description.as_str() {
        "clear sky" => weather_text.bright_yellow(),
        "few clouds" | "scattered clouds" | "broken clouds" => weather_text.bright_blue(),
        "overcast clouds" | "mist" | "haze" | "smoke" | "fog" | "sand" | "dust" | "squalls" => weather_text.dimmed(),
        "shower rain" | "rain" | "thunderstorm" | "snow" => weather_text.bright_cyan(),
        _ => weather_text.normal(),
    };

    println!("{}", weather_text_color);
}

fn get_temp_emoji(temp: f64) -> &'static str {
    if temp < 0.0 {
        "❄️"
    } else if temp < 10.0 {
        "🥶"
    } else if temp < 20.0 {
        "😊"
    } else if temp < 30.0 {
        "🌞"
    } else {
        "🔥"
    }
}
