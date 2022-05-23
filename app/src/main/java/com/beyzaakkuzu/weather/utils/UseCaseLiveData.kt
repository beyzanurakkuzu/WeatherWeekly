package com.beyzaakkuzu.weather.utils

import androidx.lifecycle.LiveData

abstract class UseCaseLiveData<M,P,R> {
    abstract fun getRepository():R
    abstract fun buildUseCaseObservable(params:P?):LiveData<M>
    abstract class Params
    fun execute(params:P?) : LiveData<M>{
        return buildUseCaseObservable(params)
    }

}