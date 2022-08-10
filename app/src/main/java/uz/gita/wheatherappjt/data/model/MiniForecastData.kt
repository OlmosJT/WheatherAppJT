package uz.gita.wheatherappjt.data.model

data class MiniForecastData(
    val date: String,
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_f: Double,
    val status_code: Int,
)
