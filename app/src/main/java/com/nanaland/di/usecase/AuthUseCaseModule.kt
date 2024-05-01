package com.nanaland.di.usecase

import com.nanaland.domain.repository.AuthRepository
import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.nanaland.domain.usecase.auth.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {

    @Singleton
    @Provides
    fun provideReissueTokenUseCase(
        repository: AuthRepository
    ): ReissueAccessTokenUseCase {
        return ReissueAccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignInUseCase(
        repository: AuthRepository
    ): SignInUseCase {
        return SignInUseCase(repository)
    }
}