package com.beyzaakkuzu.weather.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider


class BaseActivity<VM: BaseViewModel, DB:ViewDataBinding>(
    @LayoutRes private val layoutResID:Int,viewModelClass: Class<VM>

):AppCompatActivity() {
    protected lateinit var binding:DB

    val viewModel:VM by lazy{
        ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this,layoutResID) as DB

binding.run {
   // setVariable(BR.viewModel,viewModel)
    lifecycleOwner = this@BaseActivity
}    }
}