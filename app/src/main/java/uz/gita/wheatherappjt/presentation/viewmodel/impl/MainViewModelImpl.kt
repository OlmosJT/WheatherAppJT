package uz.gita.wheatherappjt.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.wheatherappjt.R
import uz.gita.wheatherappjt.domain.repository.AppRepository
import uz.gita.wheatherappjt.presentation.viewmodel.MainViewModel
import uz.gita.wheatherappjt.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: AppRepository
): ViewModel(), MainViewModel {
    override val cityLiveData = MutableLiveData<String>()
    override val dayQuoteLiveData = MutableLiveData<Int>()
    override val dateLiveData = MutableLiveData<String>()
    override val currIconLiveData = MutableLiveData<Int>()
    override val currTempLiveData = MutableLiveData<String>()
    override val currWindSpeedLiveData = MutableLiveData<String>()
    override val currWaterPercentLIveData = MutableLiveData<String>()

    override val miniIcon1LiveData = MutableLiveData<Int>()
    override val miniForecast1LiveData = MutableLiveData<String>()
    override val miniForecast1Date = MutableLiveData<String>()
    override val miniIcon2LiveData = MutableLiveData<Int>()
    override val miniForecast2LiveData = MutableLiveData<String>()
    override val miniForecast2Date = MutableLiveData<String>()
    override val miniIcon3LiveData = MutableLiveData<Int>()
    override val miniForecast3LiveData = MutableLiveData<String>()
    override val miniForecast3Date = MutableLiveData<String>()

    override val errorLiveData = MutableLiveData<String>()
    override val noConnectionLiveData = MutableLiveData<Boolean>()

    override fun loadForecastData(q: String){
        if(isConnected()) {
            repository.getForecastDataForDays(q = q).onEach {
                it.onSuccess { data ->
                    cityLiveData.value = data.name
                    dateLiveData.value = data.curr_day_date
                    when(data.curr_condition_code) {
                        1000 -> {
                            currIconLiveData.value = R.drawable.ic_sunny
                            dayQuoteLiveData.value = R.string.day_quote_sunny
                        }
                        1003 or 1006 or 1009 or 1030 -> {
                            currIconLiveData.value = R.drawable.ic_cloudy
                            dayQuoteLiveData.value = R.string.day_quote_rainy
                        }
                        1063 or 1069 or 1180 or 1183 or 1186 or 1189 or 1192 or 1195 -> {
                            currIconLiveData.value = R.drawable.ic_rainy
                            dayQuoteLiveData.value = R.string.day_quote_rainy
                        }
                        else -> {
                            currIconLiveData.value = R.drawable.ic_snowy
                            dayQuoteLiveData.value = R.string.day_quote_snowy
                        }
                    }
                    currTempLiveData.value = "${data.curr_temp_c} ºC"
                    currWindSpeedLiveData.value = "${data.curr_wind_mph} mph"
                    currWaterPercentLIveData.value = "${data.currWater} %"

                    miniIcon1LiveData.value = data.miniForecastDataList[0].status_code
                    miniForecast1LiveData.value = "${data.miniForecastDataList[0].mintemp_c}/${data.miniForecastDataList[0].maxtemp_c}"
                    miniForecast1Date.value = data.miniForecastDataList[0].date

                    miniIcon2LiveData.value = data.miniForecastDataList[1].status_code
                    miniForecast2LiveData.value = "${data.miniForecastDataList[1].mintemp_c}/${data.miniForecastDataList[1].maxtemp_c}"
                    miniForecast2Date.value = data.miniForecastDataList[1].date

                    miniIcon3LiveData.value = data.miniForecastDataList[2].status_code
                    miniForecast3LiveData.value = "${data.miniForecastDataList[2].mintemp_c}/${data.miniForecastDataList[2].maxtemp_c}"
                    miniForecast3Date.value = data.miniForecastDataList[2].date

                }

                it.onFailure { throwable ->
                    errorLiveData.value = throwable.message
                }
            }.launchIn(viewModelScope)
        } else {
            noConnectionLiveData.value = true
        }
    }

}


/*
        useCase.getForecastData(q).onEach { resultData ->
            resultData.onSuccess {
                cityLiveData.value = this.name
                dateLiveData.value = this.curr_day_date
                when(this.curr_condition_code) {
                    1000 -> {
                        currIconLiveData.value = R.drawable.ic_sunny
                        dayQuoteLiveData.value = R.string.day_quote_sunny
                    }
                    1003 or 1006 or 1009 or 1030 -> {
                        currIconLiveData.value = R.drawable.ic_cloudy
                        dayQuoteLiveData.value = R.string.day_quote_rainy
                    }
                    1063 or 1069 or 1180 or 1183 or 1186 or 1189 or 1192 or 1195 -> {
                        currIconLiveData.value = R.drawable.ic_rainy
                        dayQuoteLiveData.value = R.string.day_quote_rainy
                    }
                    else -> {
                        currIconLiveData.value = R.drawable.ic_snowy
                        dayQuoteLiveData.value = R.string.day_quote_snowy
                    }
                }
                currTempLiveData.value = "${this.curr_temp_c} ºC"
                currWindSpeedLiveData.value = "${this.curr_wind_mph} mph"
                currWaterPercentLIveData.value = "${this.currWater} %"
            }

            resultData.onText {
                Log.d("viewmodel", "OnTextError: ${this.message}")
            }

            resultData.onResource {
                Log.d("viewmodel", "OnResourceError")
            }
        }.launchIn(viewModelScope)
         */