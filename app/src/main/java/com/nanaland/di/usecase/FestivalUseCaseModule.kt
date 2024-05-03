package com.nanaland.di.usecase

import com.nanaland.domain.repository.FestivalRepository
import com.nanaland.domain.usecase.festival.GetEndedFestivalListUseCase
import com.nanaland.domain.usecase.festival.GetFestivalContentUseCase
import com.nanaland.domain.usecase.festival.GetMonthlyFestivalListUseCase
import com.nanaland.domain.usecase.festival.GetSeasonalFestivalListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FestivalUseCaseModule {

    @Singleton
    @Provides
    fun provideGetFestivalContentUseCase(
        repository: FestivalRepository
    ): GetFestivalContentUseCase {
        return GetFestivalContentUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMonthlyFestivalListUseCase(
        repository: FestivalRepository
    ): GetMonthlyFestivalListUseCase {
        return GetMonthlyFestivalListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSeasonalFestivalListUseCase(
        repository: FestivalRepository
    ): GetSeasonalFestivalListUseCase {
        return GetSeasonalFestivalListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetEndedFestivalListUseCase(
        repository: FestivalRepository
    ): GetEndedFestivalListUseCase {
        return GetEndedFestivalListUseCase(repository)
    }
}