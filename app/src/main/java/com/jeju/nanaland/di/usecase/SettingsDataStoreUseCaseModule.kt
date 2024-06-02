package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.domain.usecase.settingsdatastore.ClearUserSettingsDataStoreUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetValueUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveValueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsDataStoreUseCaseModule {

    @Singleton
    @Provides
    fun provideGetValueUseCase(
        repository: UserSettingsDataStoreRepository
    ): GetValueUseCase {
        return GetValueUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveValueUseCase(
        repository: UserSettingsDataStoreRepository
    ): SaveValueUseCase {
        return SaveValueUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideClearUserSettingsDataStoreUseCase(
        repository: UserSettingsDataStoreRepository
    ): ClearUserSettingsDataStoreUseCase {
        return ClearUserSettingsDataStoreUseCase(repository)
    }
}