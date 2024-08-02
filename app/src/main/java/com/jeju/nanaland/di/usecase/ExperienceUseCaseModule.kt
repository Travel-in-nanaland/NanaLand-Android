package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.ExperienceRepository
import com.jeju.nanaland.domain.usecase.experience.GetExperienceContentUseCase
import com.jeju.nanaland.domain.usecase.experience.GetExperienceListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExperienceUseCaseModule {

    @Singleton
    @Provides
    fun provideGetExperienceContentUseCase(
        repository: ExperienceRepository
    ): GetExperienceContentUseCase {
        return GetExperienceContentUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetExperienceListUseCase(
        repository: ExperienceRepository
    ): GetExperienceListUseCase {
        return GetExperienceListUseCase(repository)
    }
}