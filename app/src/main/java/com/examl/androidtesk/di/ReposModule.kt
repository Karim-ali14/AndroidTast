package com.examl.androidtesk.di

import com.examl.androidtesk.data.api.ApiService
import com.examl.androidtesk.data.repository.MainRepository
import com.examl.androidtesk.data.repository.MainRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.annotation.Nullable

@Module
@InstallIn(ViewModelComponent::class)
object ReposModule {

    @Provides
    fun provideMainRepository(@Nullable service: ApiService) :MainRepository{
        return MainRepositoryImp(service)
    }
}