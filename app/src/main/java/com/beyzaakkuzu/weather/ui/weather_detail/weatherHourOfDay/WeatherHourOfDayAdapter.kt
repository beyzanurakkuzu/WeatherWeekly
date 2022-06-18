package com.beyzaakkuzu.weather.ui.weather_detail.weatherHourOfDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.beyzaakkuzu.weather.core.BaseAdapter
import com.beyzaakkuzu.weather.databinding.HourOfDayWeatherItemBinding
import com.beyzaakkuzu.weather.domain.model.ListItem

class WeatherHourOfDayAdapter(private val callBack: (ListItem) -> Unit) : BaseAdapter<ListItem>(
    diffCallback
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = HourOfDayWeatherItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = WeatherHourOfDayItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as HourOfDayWeatherItemBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}