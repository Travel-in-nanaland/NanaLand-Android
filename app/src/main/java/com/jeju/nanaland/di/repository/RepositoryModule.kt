package com.jeju.nanaland.di.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jeju.nanaland.data.api.AuthApi
import com.jeju.nanaland.data.api.FavoriteApi
import com.jeju.nanaland.data.api.FestivalApi
import com.jeju.nanaland.data.api.MarketApi
import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.data.api.NanaPickApi
import com.jeju.nanaland.data.api.NatureApi
import com.jeju.nanaland.data.api.SearchApi
import com.jeju.nanaland.data.repository.AuthDataStoreRepositoryImpl
import com.jeju.nanaland.data.repository.AuthRepositoryImpl
import com.jeju.nanaland.data.repository.FavoriteRepositoryImpl
import com.jeju.nanaland.data.repository.FestivalRepositoryImpl
import com.jeju.nanaland.data.repository.MarketRepositoryImpl
import com.jeju.nanaland.data.repository.MemberRepositoryImpl
import com.jeju.nanaland.data.repository.NanaPickRepositoryImpl
import com.jeju.nanaland.data.repository.NatureRepositoryImpl
import com.jeju.nanaland.data.repository.RecentSearchDataStoreRepositoryImpl
import com.jeju.nanaland.data.repository.SearchRepositoryImpl
import com.jeju.nanaland.data.repository.UserSettingsDataStoreRepositoryImpl
import com.jeju.nanaland.di.datastore.DataStoreModule
import com.jeju.nanaland.domain.repository.AuthDataStoreRepository
import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.repository.MarketRepository
import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.repository.NanaPickRepository
import com.jeju.nanaland.domain.repository.NatureRepository
import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository
import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNanaPickRepository(
        nanaPickApi: NanaPickApi
    ): NanaPickRepository {
        return NanaPickRepositoryImpl(nanaPickApi)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchApi: SearchApi
    ): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        favoriteApi: FavoriteApi
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteApi)
    }

    @Singleton
    @Provides
    fun provideMarketRepository(
        marketApi: MarketApi
    ): MarketRepository {
        return MarketRepositoryImpl(marketApi)
    }

    @Singleton
    @Provides
    fun provideNatureRepository(
        natureApi: NatureApi
    ): NatureRepository {
        return NatureRepositoryImpl(natureApi)
    }

    @Singleton
    @Provides
    fun provideMemberRepository(
        memberApi: MemberApi
    ): MemberRepository {
        return MemberRepositoryImpl(memberApi)
    }

    @Singleton
    @Provides
    fun provideFestivalRepository(
        festivalApi: FestivalApi
    ): FestivalRepository {
        return FestivalRepositoryImpl(festivalApi)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authApi: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Singleton
    @Provides
    fun provideAuthDataStoreRepository(
        @DataStoreModule.AuthDataStore dataStore: DataStore<Preferences>
    ): AuthDataStoreRepository {
        return AuthDataStoreRepositoryImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideRecentSearchDataStoreRepository(
        @DataStoreModule.RecentSearchDataStore dataStore: DataStore<Preferences>
    ): RecentSearchDataStoreRepository {
        return RecentSearchDataStoreRepositoryImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideUserSettingsDataStoreRepository(
        @DataStoreModule.UserSettingsDataStore dataStore: DataStore<Preferences>
    ): UserSettingsDataStoreRepository {
        return UserSettingsDataStoreRepositoryImpl(dataStore)
    }
}