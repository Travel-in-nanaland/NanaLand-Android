package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.domain.usecase.settingsdatastore.ClearUserSettingsDataStoreUseCase
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
    fun provideClearUserSettingsDataStoreUseCase(
        repository: UserSettingsDataStoreRepository
    ): ClearUserSettingsDataStoreUseCase {
        return ClearUserSettingsDataStoreUseCase(repository)
    }
}