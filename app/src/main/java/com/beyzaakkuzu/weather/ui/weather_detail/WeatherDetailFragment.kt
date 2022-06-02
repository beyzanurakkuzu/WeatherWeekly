package com.beyzaakkuzu.weather.ui.weather_detail

import android.transition.TransitionInflater
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beyzaakkuzu.weather.R
import com.beyzaakkuzu.weather.core.BaseFragment
import com.beyzaakkuzu.weather.databinding.FragmentWeatherDetailBinding
import com.beyzaakkuzu.weather.domain.model.ListItem
import com.beyzaakkuzu.weather.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayAdapter
import com.beyzaakkuzu.weather.utils.extensions.observeWith
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(
    R.layout.forecast_item, WeatherDetailViewModel::class.java
) {
    private val weatherDetailFragmentsArgs: WeatherDetailFragmentArgs by navArgs()
    var disposable = CompositeDisposable()
    override fun init() {
        super.init()
        binding.viewModel?.weatherItem?.set(weatherDetailFragmentsArgs.weatherItem)
        binding.viewModel?.selectDayDate =
            weatherDetailFragmentsArgs.weatherItem.dtTxt?.substringBefore(
                " "
            )

        binding.viewModel?.getForecast()?.observeWith(viewLifecycleOwner) {
            binding.viewModel?.selectedDayForecastLiveData
                ?.postValue(
                    it.list?.filter { item ->
                        item.dtTxt?.substringBefore(" ") == binding.viewModel?.selectDayDate
                    }
                )
        }

        binding.viewModel?.selectedDayForecastLiveData?.observeWith(
            viewLifecycleOwner
        ) {
            initWeatherHourOfDayAdapter(it)
        }

        binding.cardViewTv.setOnClickListener {
            findNavController().popBackStack()
        }

        val inflateTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = inflateTransition
    }

    private fun initWeatherHourOfDayAdapter(list: List<ListItem>){
        val adapter = WeatherHourOfDayAdapter{
            item ->
        }

        binding.recyclerViewHourOfDay.adapter =adapter
        (binding.recyclerViewHourOfDay.adapter as WeatherHourOfDayAdapter).submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}