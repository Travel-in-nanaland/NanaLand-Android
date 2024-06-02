package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.AuthDataStoreRepository
import com.jeju.nanaland.domain.usecase.authdatastore.ClearAuthDataStoreUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.jeju.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDataStoreUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAccessTokenUseCase(
        repository: AuthDataStoreRepository
    ): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRefreshTokenUseCase(
        repository: AuthDataStoreRepository
    ): GetRefreshTokenUseCase {
        return GetRefreshTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveAccessTokenUseCase(
        repository: AuthDataStoreRepository
    ): SaveAccessTokenUseCase {
        return SaveAccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveRefreshTokenUseCase(
        repository: AuthDataStoreRepository
    ): SaveRefreshTokenUseCase {
        return SaveRefreshTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideClearAuthDataStoreUseCase(
        repository: AuthDataStoreRepository
    ): ClearAuthDataStoreUseCase {
        return ClearAuthDataStoreUseCase(repository)
    }
}