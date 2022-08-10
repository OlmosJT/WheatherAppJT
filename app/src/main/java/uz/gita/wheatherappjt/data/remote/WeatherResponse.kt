package uz.gita.wheatherappjt.data.remote

sealed class WeatherResponse {

    // Main GSON Response
    data class ForecastResponse(
        val location: Location,
        val current: Current,
        val forecast: Forecast
    )

    // Location GSON
    data class Location(
        val name: String, // using
        val region: String, // using
        val country: String, // using
        val lat: Double,
        val lon: Double,
        val tz_id: String,
        val localtime_epoch: Int,
        val localtime: String // using
    )

    // Current GSON
    data class Current(
        val last_updated_epoch: Int,
        val last_updated: String, //using
        val temp_c: Double, // using
        val temp_f: Double, // using
        val is_day: Int, // using
        val condition: Condition, // using its .text
        val wind_mph: Double, // using
        val wind_kph: Double, // using
        val wind_degree: Int,
        val wind_dir: String,
        val pressure_mb: Double,
        val pressure_in: Double,
        val precip_mm: Double,
        val precip_in: Double,
        val humidity: Int, // using
        val cloud: Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val vis_km: Double,
        val vis_miles: Double,
        val uv: Double,
        val gust_mph: Double,
        val gust_kph: Double
    )

    // Current.Condition GSON
    data class Condition(
        val code: Int,
        val icon: String,
        val text: String // using
    )

    // Forecast GSON
    data class Forecast(
        val forecastday: List<Forecastday>
    )

    // Forecast.ForecastDay GSON
    data class Forecastday(
        val date: String,
        val date_epoch: Int,
        val day: Day,
        val astro: Astro,
        val hour: List<Hour>
    )

    // Forecast,ForecastDay.Day GSON
    data class Day(
        val avghumidity: Double,
        val avgtemp_c: Double,
        val avgtemp_f: Double,
        val avgvis_km: Double,
        val avgvis_miles: Double,
        val condition: Condition,
        val daily_chance_of_rain: Int,
        val daily_chance_of_snow: Int,
        val daily_will_it_rain: Int,
        val daily_will_it_snow: Int,
        val maxtemp_c: Double,
        val maxtemp_f: Double,
        val maxwind_kph: Double,
        val maxwind_mph: Double,
        val mintemp_c: Double,
        val mintemp_f: Double,
        val totalprecip_in: Double,
        val totalprecip_mm: Double,
        val uv: Double
    )

    // Forecast.ForecastDay.Astro GSON
    data class Astro(
        val moon_illumination: String,
        val moon_phase: String,
        val moonrise: String,
        val moonset: String,
        val sunrise: String,
        val sunset: String
    )

    // Forecast.ForecastDay.Hour GSON
    data class Hour(
        val chance_of_rain: Int,
        val chance_of_snow: Int,
        val cloud: Int,
        val condition: Condition,
        val dewpoint_c: Double,
        val dewpoint_f: Double,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val gust_kph: Double,
        val gust_mph: Double,
        val heatindex_c: Double,
        val heatindex_f: Double,
        val humidity: Int,
        val is_day: Int,
        val precip_in: Double,
        val precip_mm: Double,
        val pressure_in: Double,
        val pressure_mb: Double,
        val temp_c: Double,
        val temp_f: Double,
        val time: String,
        val time_epoch: Int,
        val uv: Double,
        val vis_km: Double,
        val vis_miles: Double,
        val will_it_rain: Int,
        val will_it_snow: Int,
        val wind_degree: Int,
        val wind_dir: String,
        val wind_kph: Double,
        val wind_mph: Double,
        val windchill_c: Double,
        val windchill_f: Double
    )

}
