package com.nanaland.di.usecase

import com.nanaland.domain.repository.RecentSearchDataStoreRepository
import com.nanaland.domain.usecase.recentsearchdatastore.AddRecentSearchUseCase
import com.nanaland.domain.usecase.recentsearchdatastore.DeleteRecentSearchUseCase
import com.nanaland.domain.usecase.recentsearchdatastore.GetAllRecentSearchUseCase
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
}