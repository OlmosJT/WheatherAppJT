package uz.gita.wheatherappjt.data.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServiceApi {
    @GET("v1/forecast.json")
    suspend fun requestForecastInDays(
        @Query("key") api_key: String = "6dc2485ca0eb4cd0aa395847220808",
        @Query("q") locationOrCity: String,
        @Query("days") days: Int = 3,
    ): Response<WeatherResponse.ForecastResponse>
}