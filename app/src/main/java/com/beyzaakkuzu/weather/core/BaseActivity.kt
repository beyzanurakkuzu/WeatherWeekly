package com.beyzaakkuzu.weather.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.beyzaakkuzu.weather.BR


open class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes private val layoutResID: Int, viewModelClass: Class<VM>

) : AppCompatActivity() {
    protected lateinit var binding: DB

    val viewModel: VM by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResID) as DB

        binding.run {
            setVariable(BR.viewModel,viewModel)
            lifecycleOwner = this@BaseActivity
        }
    }
}

