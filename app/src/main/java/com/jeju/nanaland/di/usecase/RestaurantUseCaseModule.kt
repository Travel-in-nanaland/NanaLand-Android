package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.RestaurantRepository
import com.jeju.nanaland.domain.usecase.restaurant.GetRestaurantContentUseCase
import com.jeju.nanaland.domain.usecase.restaurant.GetRestaurantListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantUseCaseModule {

    @Singleton
    @Provides
    fun provideGetRestaurantContentUseCase(
        repository: RestaurantRepository
    ): GetRestaurantContentUseCase {
        return GetRestaurantContentUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRestaurantListUseCase(
        repository: RestaurantRepository
    ): GetRestaurantListUseCase {
        return GetRestaurantListUseCase(repository)
    }
}