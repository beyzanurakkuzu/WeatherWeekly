package com.beyzaakkuzu.weather.ui.dashboard

import android.transition.TransitionInflater
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzaakkuzu.weather.R
import com.beyzaakkuzu.weather.core.BaseFragment
import com.beyzaakkuzu.weather.databinding.FragmentDashboardBinding
import com.beyzaakkuzu.weather.domain.model.ListItem
import com.beyzaakkuzu.weather.domain.usecase.CurrentWeatherUseCase
import com.beyzaakkuzu.weather.domain.usecase.ForecastUseCase
import com.beyzaakkuzu.weather.other.Constants
import com.beyzaakkuzu.weather.ui.dashboard.forecast.ForecastAdapter
import com.beyzaakkuzu.weather.ui.main.MainActivity
import com.beyzaakkuzu.weather.utils.extensions.isNetworkAvailable
import com.beyzaakkuzu.weather.utils.extensions.observeWith

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard, DashboardViewModel::class.java
) {

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(
            R.transition.move
        )
        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "")
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")

        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            binding.viewModel?.setCurrentWeatherParams(
                CurrentWeatherUseCase.CurrentWeatherParams(
                    lat, lon, isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
            binding.viewModel?.setForecastParams(
                ForecastUseCase.ForecastParams(
                    lat, lon, isNetworkAvailable(requireContext()),
                    Constants.Coords.METRIC
                )
            )
            binding.viewModel?.getForecastViewState()?.observeWith(viewLifecycleOwner) {
                with(binding) {
                    viewState = it
                    it.data?.list?.let { forecast ->
                        initForecast(forecast)
                    }
                    (activity as MainActivity).viewModel.toolbarTitle.set( //toolbar: seçilen şehir veya ülkenin ismi buraya yazılır
                        it.data?.city?.getCityAndCountry()
                    )
                }
            }
            binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
                viewLifecycleOwner
            ) {
                with(binding) {
                    containerForecast.viewState = it
                }
            }
        }


    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, tempMaxMin ->
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(
                    item
                )
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to cardView.transitionName,
                                forecastIcon to forecastIcon.transitionName,
                                dayOfWeek to dayOfWeek.transitionName,
                                tempMaxMin to tempMaxMin.transitionName
                            )
                        )
                        .build()
                )
        }
        binding.recyclerdaydetail.adapter = adapter
        binding.recyclerdaydetail.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        postponeEnterTransition()
        binding.recyclerdaydetail.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerdaydetail.adapter as ForecastAdapter).submitList(list)
    }
}