package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.data.repository.ReportRepositoryImpl
import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.usecase.report.InformationModificationProposalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReportUseCaseModule {

    @Singleton
    @Provides
    fun provideInformationModificationProposalUseCase(
        repository: ReportRepository
    ): InformationModificationProposalUseCase {
        return InformationModificationProposalUseCase(repository)
    }
}