package com.beyzaakkuzu.weather.ui.dashboard.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.beyzaakkuzu.weather.core.BaseAdapter
import com.beyzaakkuzu.weather.databinding.ForecastItemBinding
import com.beyzaakkuzu.weather.domain.model.ListItem

class ForecastAdapter(
    private val callBack: (ListItem, View, View, View, View) -> Unit
) : BaseAdapter<ListItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val binding = ForecastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = ForecastItemViewModel()
        binding.viewModel = viewModel
        binding.cardViewTv.setOnClickListener {
            binding.viewModel?.item?.get()?.let {
                callBack(
                    it,
                    binding.cardViewTv,
                    binding.imageViewForecastIcon,
                    binding.textViewDayOfWeek,
                    binding.linearLayoutTempMaxMin
                )
            }
        }
        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ForecastItemBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
