# Weather CLI

A simple command-line interface (CLI) application to get current weather information for any city using the OpenWeatherMap API. This project demonstrates how to use Rust for creating a CLI, reading environment variables, making HTTP requests, and handling JSON responses.

## Features

- Fetches current weather data for any city.
- Displays weather description, temperature, humidity, pressure, and wind speed.
- Colored and emoji-enhanced output for better user experience.
- Allows multiple queries in one session.

## Prerequisites

- Rust installed on your machine. You can install Rust using [rustup](https://www.rust-lang.org/tools/install).
- An API key from [OpenWeatherMap](https://home.openweathermap.org/users/sign_up).

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/gabrielalmir/weather-cli.git
   cd weather-cli

2. Create a `.env` file in the root directory of the project and add your OpenWeatherMap API key:
    ```sh
    WEATHER_API_TOKEN=your_api_key_here
    ```

3. Build the project:
   ```sh
   cargo build --release
    ```

4. Run the CLI:
    ```sh
    cargo run
     ```
