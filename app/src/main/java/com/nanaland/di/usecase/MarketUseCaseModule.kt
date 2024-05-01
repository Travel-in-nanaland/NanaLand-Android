package com.nanaland.di.usecase

import com.nanaland.domain.repository.MarketRepository
import com.nanaland.domain.usecase.market.GetMarketContentUseCase
import com.nanaland.domain.usecase.market.GetMarketListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarketUseCaseModule {

    // 전통시장 상세 정보 조회
    @Singleton
    @Provides
    fun provideGetMarketContentUseCase(
        repository: MarketRepository
    ): GetMarketContentUseCase {
        return GetMarketContentUseCase(repository)
    }

    // 전통시장 리스트 조회
    @Singleton
    @Provides
    fun provideGetMarketListUseCase(
        repository: MarketRepository
    ): GetMarketListUseCase {
        return GetMarketListUseCase(repository)
    }
}