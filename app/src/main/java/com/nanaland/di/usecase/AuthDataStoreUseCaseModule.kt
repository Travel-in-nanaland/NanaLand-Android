package com.nanaland.di.usecase

import com.nanaland.domain.repository.AuthDataStoreRepository
import com.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
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
}