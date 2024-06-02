package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.AddRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.ClearRecentSearchDataStoreUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.DeleteRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.GetAllRecentSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecentSearchDataStoreUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllSearchUseCase(
        repository: RecentSearchDataStoreRepository
    ): GetAllRecentSearchUseCase {
        return GetAllRecentSearchUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddRecentSearchUseCase(
        repository: RecentSearchDataStoreRepository
    ): AddRecentSearchUseCase {
        return AddRecentSearchUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteRecentSearchUseCase(
        repository: RecentSearchDataStoreRepository
    ): DeleteRecentSearchUseCase {
        return DeleteRecentSearchUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideClearRecentSearchDataStoreUseCase(
        repository: RecentSearchDataStoreRepository
    ): ClearRecentSearchDataStoreUseCase {
        return ClearRecentSearchDataStoreUseCase(repository)
    }
}