package com.nanaland.di.usecase

import com.nanaland.domain.repository.NatureRepository
import com.nanaland.domain.usecase.nature.GetNatureContentUseCase
import com.nanaland.domain.usecase.nature.GetNatureListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NatureUseCaseModule {


    // 7대 자연 상세 정보 조회
    @Singleton
    @Provides
    fun provideGetNatureContentUseCase(
        repository: NatureRepository
    ): GetNatureContentUseCase {
        return GetNatureContentUseCase(repository)
    }

    // 7대 자연 리스트 조회
    @Singleton
    @Provides
    fun provideGetNatureListUseCase(
        repository: NatureRepository
    ): GetNatureListUseCase {
        return GetNatureListUseCase(repository)
    }
}