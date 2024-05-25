package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.usecase.member.GetRecommendedPostUseCase
import com.jeju.nanaland.domain.usecase.member.GetUserProfileUseCase
import com.jeju.nanaland.domain.usecase.member.SignOutUseCase
import com.jeju.nanaland.domain.usecase.member.UpdateLanguageUseCase
import com.jeju.nanaland.domain.usecase.member.UpdateUserTypeUseCase
import com.jeju.nanaland.domain.usecase.member.WithdrawUseCase
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
    fun provideGetUserProfileUseCase(
        repository: MemberRepository
    ): GetUserProfileUseCase {
        return GetUserProfileUseCase(repository)
    }

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

    @Singleton
    @Provides
    fun provideWithDrawUseCase(
        repository: MemberRepository
    ): WithdrawUseCase {
        return WithdrawUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignOutUseCase(
        repository: MemberRepository
    ): SignOutUseCase {
        return SignOutUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateLanguageUseCase(
        repository: MemberRepository
    ): UpdateLanguageUseCase {
        return UpdateLanguageUseCase(repository)
    }
}