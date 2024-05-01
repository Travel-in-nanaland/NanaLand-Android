package com.nanaland.di.usecase

import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.nanaland.domain.usecase.auth.SignInUseCase
import com.nanaland.domain.usecase.member.UpdateUserTypeUseCase
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