package uz.gita.wheatherappjt.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.wheatherappjt.data.model.ForecastData
import uz.gita.wheatherappjt.utils.ResultData

interface AppRepository {
    fun getForecastDataForDays(q: String): Flow<Result<ForecastData>>
}