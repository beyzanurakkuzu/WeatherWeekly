package com.beyzaakkuzu.weather.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context):SharedPreferences
    = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideMoshi():Moshi= Moshi.Builder().build()
}