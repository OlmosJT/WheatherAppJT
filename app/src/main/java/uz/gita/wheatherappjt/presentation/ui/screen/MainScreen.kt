package uz.gita.wheatherappjt.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.wheatherappjt.R
import uz.gita.wheatherappjt.databinding.ScreenMainBinding
import uz.gita.wheatherappjt.presentation.viewmodel.MainViewModel
import uz.gita.wheatherappjt.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen: Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT < 16) {
//            requireActivity().window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        } else {
//            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadForecastData("Tashkent")

        binding.btnArrow.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
            val height: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16F, resources.displayMetrics).toInt()
            val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28F, resources.displayMetrics).toInt()

            if(motionEvent.action == MotionEvent.ACTION_DOWN) {
                layoutParams.width = width +5
                layoutParams.height = height +5
                binding.btnArrow.layoutParams = layoutParams
            }
            if(motionEvent.action == MotionEvent.ACTION_UP) {
                layoutParams.width = width
                layoutParams.height = height
                binding.btnArrow.layoutParams = layoutParams
            }

            return@OnTouchListener false
        })

        viewModel.cityLiveData.observe(viewLifecycleOwner, cityObserver)
        viewModel.currIconLiveData.observe(viewLifecycleOwner, currIconObserver)
        viewModel.currTempLiveData.observe(viewLifecycleOwner, currTempObserver)
        viewModel.dateLiveData.observe(viewLifecycleOwner, dateObserver)
        viewModel.dayQuoteLiveData.observe(viewLifecycleOwner, dayQuoteObserver)
        viewModel.currWaterPercentLIveData.observe(viewLifecycleOwner, waterPercentObserver)
        viewModel.currWindSpeedLiveData.observe(viewLifecycleOwner, currWindSpeedObserver)
        viewModel.noConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)

        viewModel.miniForecast1Date.observe(viewLifecycleOwner, Observer<String> {
            binding.miniDay1.text = it
        })

        viewModel.miniForecast1LiveData.observe(viewLifecycleOwner, Observer<String>{
            binding.miniTemperature1.text = it
        })

        viewModel.miniIcon1LiveData.observe(viewLifecycleOwner, Observer<Int> {
            when(it) {
                1000 -> {
                    binding.miniIcon1.setImageResource(R.drawable.ic_sunny)
                }
                1003 or 1006 or 1009 or 1030 -> {
                    binding.miniIcon1.setImageResource( R.drawable.ic_cloudy )
                }
                1063 or 1069 or 1180 or 1183 or 1186 or 1189 or 1192 or 1195 -> {
                    binding.miniIcon1.setImageResource( R.drawable.ic_rainy )
                }
                else -> {
                    binding.miniIcon1.setImageResource( R.drawable.ic_snowy )
                }
            }
        })

        viewModel.miniForecast2Date.observe(viewLifecycleOwner, Observer<String> {
            binding.miniDay2.text = it
        })

        viewModel.miniForecast2LiveData.observe(viewLifecycleOwner, Observer<String>{
            binding.miniTemperature2.text = it
        })

        viewModel.miniIcon2LiveData.observe(viewLifecycleOwner, Observer<Int> {
            when(it) {
                1000 -> {
                    binding.miniIcon2.setImageResource(R.drawable.ic_sunny)
                }
                1003 or 1006 or 1009 or 1030 -> {
                    binding.miniIcon2.setImageResource( R.drawable.ic_cloudy )
                }
                1063 or 1069 or 1180 or 1183 or 1186 or 1189 or 1192 or 1195 -> {
                    binding.miniIcon2.setImageResource( R.drawable.ic_rainy )
                }
                else -> {
                    binding.miniIcon2.setImageResource( R.drawable.ic_snowy )
                }
            }
        })

        viewModel.miniForecast3Date.observe(viewLifecycleOwner, Observer<String> {
            binding.miniDay3.text = it
        })

        viewModel.miniForecast3LiveData.observe(viewLifecycleOwner, Observer<String>{
            binding.miniTemperature3.text = it
        })

        viewModel.miniIcon3LiveData.observe(viewLifecycleOwner, Observer<Int> {
            when(it) {
                1000 -> {
                    binding.miniIcon3.setImageResource(R.drawable.ic_sunny)
                }
                1003 or 1006 or 1009 or 1030 -> {
                    binding.miniIcon3.setImageResource( R.drawable.ic_cloudy )
                }
                1063 or 1069 or 1180 or 1183 or 1186 or 1189 or 1192 or 1195 -> {
                    binding.miniIcon3.setImageResource( R.drawable.ic_rainy )
                }
                else -> {
                    binding.miniIcon3.setImageResource( R.drawable.ic_snowy )
                }
            }
        })
    }

    private val cityObserver = Observer<String>{
        binding.textCity.text = it
    }

    private val currIconObserver = Observer<Int> {
        binding.iconStatus.setImageResource(it)
    }

    private val currTempObserver = Observer<String> {
        binding.temperature.text = it
    }

    private val dateObserver = Observer<String> {
        binding.textDate.text = it
    }

    private val dayQuoteObserver = Observer<Int> {
        binding.dayQuote.text = resources.getString(it)
    }

    private val waterPercentObserver = Observer<String> {
        binding.water.text = it
    }

    private val currWindSpeedObserver = Observer<String> {
        binding.wind.text = it
    }

    private val notConnectionObserver = Observer<Boolean> {
        if(it){
            // no internet
            binding.notConnected.visibility = View.VISIBLE
            binding.notConnectedText.visibility = View.VISIBLE
        } else {
            binding.notConnected.visibility = View.GONE
            binding.notConnectedText.visibility = View.GONE
        }
    }


}