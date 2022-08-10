package uz.gita.wheatherappjt.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData

interface MainViewModel {
    val cityLiveData: LiveData<String>
    val dateLiveData: LiveData<String>
    val currIconLiveData: LiveData<Int>
    val currTempLiveData: LiveData<String>
    val currWindSpeedLiveData: LiveData<String>
    val currWaterPercentLIveData: LiveData<String>
    val dayQuoteLiveData: LiveData<Int>

    val miniIcon1LiveData: LiveData<Int>
    val miniForecast1LiveData: LiveData<String>
    val miniForecast1Date: LiveData<String>

    val miniIcon2LiveData: LiveData<Int>
    val miniForecast2LiveData: LiveData<String>
    val miniForecast2Date: LiveData<String>

    val miniIcon3LiveData: LiveData<Int>
    val miniForecast3LiveData: LiveData<String>
    val miniForecast3Date: LiveData<String>

    val errorLiveData: LiveData<String>
    val noConnectionLiveData: LiveData<Boolean>

    fun loadForecastData(q: String)

}