package com.nanaland.di.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.nanaland.data.api.AuthApi
import com.nanaland.data.api.FavoriteApi
import com.nanaland.data.api.FestivalApi
import com.nanaland.data.api.MarketApi
import com.nanaland.data.api.MemberApi
import com.nanaland.domain.repository.NanaPickRepository
import com.nanaland.data.api.NanaPickApi
import com.nanaland.data.api.NatureApi
import com.nanaland.data.api.SearchApi
import com.nanaland.data.repository.AuthRepositoryImpl
import com.nanaland.data.repository.FavoriteRepositoryImpl
import com.nanaland.data.repository.FestivalRepositoryImpl
import com.nanaland.data.repository.MarketRepositoryImpl
import com.nanaland.data.repository.MemberRepositoryImpl
import com.nanaland.data.repository.NanaPickRepositoryImpl
import com.nanaland.data.repository.NatureRepositoryImpl
import com.nanaland.data.repository.AuthDataStoreRepositoryImpl
import com.nanaland.data.repository.RecentSearchDataStoreRepositoryImpl
import com.nanaland.data.repository.SearchRepositoryImpl
import com.nanaland.di.datastore.DataStoreModule
import com.nanaland.domain.repository.AuthRepository
import com.nanaland.domain.repository.FavoriteRepository
import com.nanaland.domain.repository.FestivalRepository
import com.nanaland.domain.repository.MarketRepository
import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.repository.NatureRepository
import com.nanaland.domain.repository.AuthDataStoreRepository
import com.nanaland.domain.repository.RecentSearchDataStoreRepository
import com.nanaland.domain.repository.SearchRepository
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
}