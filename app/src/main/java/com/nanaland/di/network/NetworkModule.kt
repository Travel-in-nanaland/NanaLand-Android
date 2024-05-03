package com.nanaland.di.network

import com.nanaland.data.api.NanaPickApi
import com.nanaland.BuildConfig
import com.nanaland.data.api.AuthApi
import com.nanaland.data.api.FavoriteApi
import com.nanaland.data.api.FestivalApi
import com.nanaland.data.api.MarketApi
import com.nanaland.data.api.MemberApi
import com.nanaland.data.api.NatureApi
import com.nanaland.data.api.SearchApi
import com.nanaland.util.network.AuthAuthenticator
import com.nanaland.util.network.LogInterceptor
import com.nanaland.util.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AccessTokenAutoAdded

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HeaderManuallyAdded

    @Singleton
    @Provides
    @AccessTokenAutoAdded
    fun provideAccessTokenNeededHttpClient(
        tokenInterceptor: TokenInterceptor,
        authenticator: AuthAuthenticator,
        logInterceptor: LogInterceptor
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)
            .authenticator(authenticator)
            .addNetworkInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @HeaderManuallyAdded
    fun provideHeaderManuallyAddedHttpClient(
        logInterceptor: LogInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @AccessTokenAutoAdded
    fun provideAccessTokenNeededRetrofitInstance(@AccessTokenAutoAdded okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @HeaderManuallyAdded
    fun provideHeaderManuallyAddedRetrofitInstance(@HeaderManuallyAdded okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(@HeaderManuallyAdded retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    // 나나's Pick Api
    @Singleton
    @Provides
    fun provideNanaPickApi(@AccessTokenAutoAdded retrofit: Retrofit): NanaPickApi {
        return retrofit.create(NanaPickApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchApi(@AccessTokenAutoAdded retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFavoriteApi(@AccessTokenAutoAdded retrofit: Retrofit): FavoriteApi {
        return retrofit.create(FavoriteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMarketApi(@AccessTokenAutoAdded retrofit: Retrofit): MarketApi {
        return retrofit.create(MarketApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNatureApi(@AccessTokenAutoAdded retrofit: Retrofit): NatureApi {
        return retrofit.create(NatureApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFestivalApi(@AccessTokenAutoAdded retrofit: Retrofit): FestivalApi {
        return retrofit.create(FestivalApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMemberApi(@AccessTokenAutoAdded retrofit: Retrofit): MemberApi {
        return retrofit.create(MemberApi::class.java)
    }
}