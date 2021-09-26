package com.evanuke.task.test.di

import com.evanuke.task.test.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiInterface(
    ): ApiInterface {
        return ApiInterface.create()
    }
}
