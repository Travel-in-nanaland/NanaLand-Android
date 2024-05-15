package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.jeju.nanaland.domain.usecase.member.UpdateUserTypeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemberUseCaseModule {

    @Singleton
    @Provides
    fun provideGetRecommendedPostUseCase(
        repository: MemberRepository
    ): GetRecommendedPostUseCase {
        return GetRecommendedPostUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateUserTypeUseCase(
        repository: MemberRepository
    ): UpdateUserTypeUseCase {
        return UpdateUserTypeUseCase(repository)
    }
}