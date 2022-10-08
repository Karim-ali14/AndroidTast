package com.examl.androidtesk.di

import android.content.Context
import com.shawky.zimozitennisapptask.data.services.PrefrencesManager.AppPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceManagerModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): AppPreferenceManager {
        return AppPreferenceManager(context)
    }

}