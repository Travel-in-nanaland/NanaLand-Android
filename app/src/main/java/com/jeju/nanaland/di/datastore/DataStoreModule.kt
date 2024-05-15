package com.jeju.nanaland.di.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthDataStore

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RecentSearchDataStore

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SettingsDataStore

    @Singleton
    @Provides
    @AuthDataStore
    fun provideAuthDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("auth") }
        )
    }

    @Singleton
    @Provides
    @RecentSearchDataStore
    fun provideRecentSearchDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("recent_search") }
        )
    }

    @Singleton
    @Provides
    @SettingsDataStore
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("settings") }
        )
    }
}