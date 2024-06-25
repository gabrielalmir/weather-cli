
# Weather Service Application

## Description

This Java application allows users to fetch and display the current weather information for a specified city and country. It uses the OpenWeatherMap API to retrieve weather data and displays it in a user-friendly format.

## Features

- Fetches real-time weather data from the OpenWeatherMap API.
- Displays weather information such as temperature, humidity, pressure, wind speed, and a description of the weather.
- Provides an interactive console interface for user input.

## Requirements

- Java 11 or higher
- Gson library
- OkHttp library
- An API key from [OpenWeatherMap](https://openweathermap.org/api)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/gabrielalmir/weather-cli.git
   cd weather-service
   ```

2. Add the required libraries to your project. If you are using Maven, add the following dependencies to your `pom.xml`:

   ```xml
   <dependencies>
       <dependency>
           <groupId>com.squareup.okhttp3</groupId>
           <artifactId>okhttp</artifactId>
           <version>4.9.3</version>
       </dependency>
       <dependency>
           <groupId>com.google.code.gson</groupId>
           <artifactId>gson</artifactId>
           <version>2.8.8</version>
       </dependency>
   </dependencies>
   ```

3. Set up your OpenWeatherMap API key as an environment variable:
   ```bash
   export OPENWEATHER_API_KEY=your_api_key_here
   ```

## Usage

1. Compile the application:
   ```bash
   javac -cp .:path_to_gson.jar:path_to_okhttp.jar br/com/gabrielalmir/*.java
   ```

2. Run the application:
   ```bash
   java -cp .:path_to_gson.jar:path_to_okhttp.jar br.com.gabrielalmir.Main
   ```

3. Follow the on-screen prompts to enter a city and country code to get the weather information.

## Example

```plaintext
Please enter your city: 
London
Please enter your country code: (e.g. US, UK, IN, BR)
UK
Looking for weather of London - UK
Weather{name='London', description='clear sky', temperature=15.0, humidity=72, pressure=1012, wind=3.6}
Do you want to check another city? (y/n) 
n
Goodbye! 😊
```

