package com.nanaland.di.usecase

import com.nanaland.domain.repository.FavoriteRepository
import com.nanaland.domain.usecase.favorite.GetAllFavoriteListUseCase
import com.nanaland.domain.usecase.favorite.GetFavoriteExperienceListUseCase
import com.nanaland.domain.usecase.favorite.GetFavoriteFestivalListUseCase
import com.nanaland.domain.usecase.favorite.GetFavoriteMarketListUseCase
import com.nanaland.domain.usecase.favorite.GetFavoriteNatureListUseCase
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteUseCaseModule {

    // 7대자연 찜리스토 조회
    @Singleton
    @Provides
    fun provideGetFavoriteNatureListUseCase(
        repository: FavoriteRepository
    ): GetFavoriteNatureListUseCase {
        return GetFavoriteNatureListUseCase(repository)
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