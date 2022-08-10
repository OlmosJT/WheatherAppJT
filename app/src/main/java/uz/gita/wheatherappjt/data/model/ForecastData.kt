package uz.gita.wheatherappjt.data.model

data class ForecastData(
    val name: String, // Tashkent
    val region: String, // Tashkent
    val country: String, // Uzbekistan
    val curr_temp_c: Double, // 32
    val curr_temp_f: Double, // 89
    val curr_condition_text: String, // Sunny
    val curr_wind_mph: Double, // using
    val curr_wind_kph: Double, // using
    val curr_condition_code: Int, // Sunny
    val curr_day_date: String,
    val currWater: Int,
    val miniForecastDataList: List<MiniForecastData> // 3 ta boradi faqat
)
