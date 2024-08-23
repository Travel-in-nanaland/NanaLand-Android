package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.NanaPickRepository
import com.jeju.nanaland.domain.usecase.nanapick.GetHomePreviewBannerUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetNanaPickContentUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetNanaPickListUseCase
import com.jeju.nanaland.domain.usecase.nanapick.GetRecommendedNanaPickListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NanaPickUseCaseModule {

    // 홈화면 미리보기 배너 4개
    @Singleton
    @Provides
    fun provideGetHomePreviewBannerUseCase(
        repository: NanaPickRepository
    ): GetHomePreviewBannerUseCase {
        return GetHomePreviewBannerUseCase(repository)
    }

    // 나나 Pick 리스트
    @Singleton
    @Provides
    fun provideGetNanaPickListUseCase(
        repository: NanaPickRepository
    ): GetNanaPickListUseCase {
        return GetNanaPickListUseCase(repository)
    }

    // 나나 Pick 콘텐츠
    @Singleton
    @Provides
    fun provideGetNanaPickContentUseCase(
        repository: NanaPickRepository
    ): GetNanaPickContentUseCase {
        return GetNanaPickContentUseCase(repository)
    }

    // 금주 추천 나나's pick
    @Singleton
    @Provides
    fun provideGetRecommendedNanaPickListUseCase(
        repository: NanaPickRepository
    ): GetRecommendedNanaPickListUseCase {
        return GetRecommendedNanaPickListUseCase(repository)
    }
}