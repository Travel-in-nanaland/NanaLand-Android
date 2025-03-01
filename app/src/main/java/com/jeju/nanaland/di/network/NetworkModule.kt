package com.jeju.nanaland.di.network

import com.google.gson.GsonBuilder
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.data.api.AuthApi
import com.jeju.nanaland.data.api.BoardApi
import com.jeju.nanaland.data.api.ExperienceApi
import com.jeju.nanaland.data.api.FTPApi
import com.jeju.nanaland.data.api.FTPWithoutTokenApi
import com.jeju.nanaland.data.api.FavoriteApi
import com.jeju.nanaland.data.api.FestivalApi
import com.jeju.nanaland.data.api.MarketApi
import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.data.api.NanaPickApi
import com.jeju.nanaland.data.api.NatureApi
import com.jeju.nanaland.data.api.ReportApi
import com.jeju.nanaland.data.api.RestaurantApi
import com.jeju.nanaland.data.api.ReviewApi
import com.jeju.nanaland.data.api.SearchApi
import com.jeju.nanaland.util.network.LogInterceptor
import com.jeju.nanaland.util.network.TokenInterceptor
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
//        authenticator: AuthAuthenticator,
        logInterceptor: LogInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
//            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(logInterceptor)
            .addInterceptor(tokenInterceptor)
//            .authenticator(authenticator)
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
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
//            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @AccessTokenAutoAdded
    fun provideAccessTokenNeededRetrofitInstance(@AccessTokenAutoAdded okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    @HeaderManuallyAdded
    fun provideHeaderManuallyAddedRetrofitInstance(@HeaderManuallyAdded okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    @Singleton
    @Provides
    fun provideReportApi(@AccessTokenAutoAdded retrofit: Retrofit): ReportApi {
        return retrofit.create(ReportApi::class.java)
    }

    @Singleton
    @Provides
    fun provideReviewApi(@AccessTokenAutoAdded retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }

    @Singleton
    @Provides
    fun provideExperienceApi(@AccessTokenAutoAdded retrofit: Retrofit): ExperienceApi {
        return retrofit.create(ExperienceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBoardApi(@AccessTokenAutoAdded retrofit: Retrofit): BoardApi {
        return retrofit.create(BoardApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantApi(@AccessTokenAutoAdded retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFTPApi(@AccessTokenAutoAdded retrofit: Retrofit): FTPApi {
        return retrofit.create(FTPApi::class.java)
    }
    @Singleton
    @Provides
    fun provideFTPWithoutTokenApi(@HeaderManuallyAdded retrofit: Retrofit): FTPWithoutTokenApi {
        return retrofit.create(FTPWithoutTokenApi::class.java)
    }
}