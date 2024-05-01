package com.nanaland.di.usecase

import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.repository.PreferencesDataStoreRepository
import com.nanaland.domain.usecase.datastore.GetAccessTokenUseCase
import com.nanaland.domain.usecase.datastore.GetRefreshTokenUseCase
import com.nanaland.domain.usecase.datastore.SaveAccessTokenUseCase
import com.nanaland.domain.usecase.datastore.SaveRefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesDataStoreUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAccessTokenUseCase(
        repository: PreferencesDataStoreRepository
    ): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRefreshTokenUseCase(
        repository: PreferencesDataStoreRepository
    ): GetRefreshTokenUseCase {
        return GetRefreshTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveAccessTokenUseCase(
        repository: PreferencesDataStoreRepository
    ): SaveAccessTokenUseCase {
        return SaveAccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveRefreshTokenUseCase(
        repository: PreferencesDataStoreRepository
    ): SaveRefreshTokenUseCase {
        return SaveRefreshTokenUseCase(repository)
    }
}