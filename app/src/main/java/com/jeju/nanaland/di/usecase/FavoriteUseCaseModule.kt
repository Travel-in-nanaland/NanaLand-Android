package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.usecase.favorite.GetAllFavoriteListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteExperienceListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteFestivalListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteMarketListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteNanaPickListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteNatureListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteRestaurantListUseCase
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteUseCaseModule {

    // 제주맛집 찜리스트 조회
    @Singleton
    @Provides
    fun provideGetFavoriteRestaurantListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteRestaurantListUseCase {
        return GetFavoriteRestaurantListUseCase(repository)
    }

    // 7대자연 찜리스토 조회
    @Singleton
    @Provides
    fun provideGetFavoriteNatureListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteNatureListUseCase {
        return GetFavoriteNatureListUseCase(repository)
    }

    // 나나스픽 찜리스트 조회
    @Singleton
    @Provides
    fun provideFavoriteNanaPickListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteNanaPickListUseCase {
        return GetFavoriteNanaPickListUseCase(repository)
    }

    // 전통시장 찜리스트 조회
    @Singleton
    @Provides
    fun provideGetFavoriteMarketListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteMarketListUseCase {
        return GetFavoriteMarketListUseCase(repository)
    }

    // 축제 찜리스트 조회
    @Singleton
    @Provides
    fun provideGetFavoriteFestivalListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteFestivalListUseCase {
        return GetFavoriteFestivalListUseCase(repository)
    }

    // 이색체험 찜리스트 조회
    @Singleton
    @Provides
    fun provideGetFavoriteExperienceListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteExperienceListUseCase {
        return GetFavoriteExperienceListUseCase(repository)
    }

    // 전체 찜리스트 조회
    @Singleton
    @Provides
    fun provideGetAllFavoriteListUseCase(
        repository: FavoriteRepository
    ): GetAllFavoriteListUseCase {
        return GetAllFavoriteListUseCase(repository)
    }

    // 좋아요 토글
    @Singleton
    @Provides
    fun provideToggleFavoriteUseCase(
        repository: FavoriteRepository
    ): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }
}