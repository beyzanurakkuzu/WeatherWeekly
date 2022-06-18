package com.beyzaakkuzu.weather.ui.search.result


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.beyzaakkuzu.weather.core.BaseAdapter
import com.beyzaakkuzu.weather.databinding.SearchResultItemBinding
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity

class SearchResultAdapter(private val callBack: (CitiesForSearchEntity) -> Unit) : BaseAdapter<CitiesForSearchEntity>(
    diffCallback
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = SearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = SearchResultItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as SearchResultItemBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}


val diffCallback = object : DiffUtil.ItemCallback<CitiesForSearchEntity>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: CitiesForSearchEntity, newItem: CitiesForSearchEntity): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: CitiesForSearchEntity, newItem: CitiesForSearchEntity): Boolean =
        oldItem.name == newItem.name
}
