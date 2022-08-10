package uz.gita.wheatherappjt.data

import uz.gita.wheatherappjt.data.model.MiniForecastData
import uz.gita.wheatherappjt.data.remote.WeatherResponse

object Mapper {
    fun WeatherResponse.Forecastday.toMiniForecastData(): MiniForecastData {
        return  MiniForecastData(
            date = this.date,
            maxtemp_f = this.day.maxtemp_f,
            maxtemp_c = this.day.maxtemp_c,
            mintemp_f = this.day.mintemp_f,
            mintemp_c = this.day.mintemp_c,
            status_code = this.day.condition.code
        )
    }
}