package uz.gita.wheatherappjt.domain.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.wheatherappjt.data.Mapper
import uz.gita.wheatherappjt.data.Mapper.toMiniForecastData
import uz.gita.wheatherappjt.data.model.ForecastData
import uz.gita.wheatherappjt.data.remote.NetworkServiceApi
import uz.gita.wheatherappjt.data.remote.WeatherResponse
import uz.gita.wheatherappjt.domain.repository.AppRepository
import uz.gita.wheatherappjt.utils.MessageData
import uz.gita.wheatherappjt.utils.ResultData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: NetworkServiceApi
): AppRepository {
    /*
    suspend fun getForecastData(q: String): ResultData<ForecastData> {
        val response = api.requestForecastInDays(locationOrCity = q) // Response<WeatherResponse.ForecastResponse>

        Log.d("TTT", "response: ${response.code()}")
        return if(response.isSuccessful) {

            val data = parseResponseToData(response.body()!!)

            ResultData.Success(data)
        } else {
            ResultData.Fail(MessageData.Text(response.message()))
        }
    }
     */

    private fun parseResponseToData(response: WeatherResponse.ForecastResponse): ForecastData {
        val location: WeatherResponse.Location = response.location
        val current: WeatherResponse.Current = response.current
        val forecast = response.forecast.forecastday.map {
            it.toMiniForecastData()
        }


        return ForecastData(
            name = location.name,
            region = location.region,
            country = location.country,
            curr_temp_c = current.temp_c,
            curr_temp_f = current.temp_f,
            curr_condition_text = current.condition.text,
            curr_wind_mph = current.wind_mph,
            curr_wind_kph = current.wind_kph,
            curr_condition_code = current.condition.code,
            curr_day_date = current.last_updated,
            currWater = current.humidity,
            miniForecastDataList = forecast
        )
    }

    override fun getForecastDataForDays(q: String) = flow<Result<ForecastData>> {
        val response = api.requestForecastInDays(locationOrCity = q)

        if(response.isSuccessful) response.body()?.let {
            emit(Result.success(parseResponseToData(it)))
        } else {
            emit(Result.failure(Exception("Error")))
        }
    }.catch { emit(Result.failure(Exception(it))) }.flowOn(Dispatchers.IO)
}