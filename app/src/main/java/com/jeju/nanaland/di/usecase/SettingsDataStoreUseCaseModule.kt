package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.domain.usecase.settingsdatastore.GetLanguageUseCase
import com.jeju.nanaland.domain.usecase.settingsdatastore.SaveLanguageUseCase
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
    fun provideGetLanguageUseCase(
        repository: UserSettingsDataStoreRepository
    ): GetLanguageUseCase {
        return GetLanguageUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveLanguageUseCase(
        repository: UserSettingsDataStoreRepository
    ): SaveLanguageUseCase {
        return SaveLanguageUseCase(repository)
    }
}