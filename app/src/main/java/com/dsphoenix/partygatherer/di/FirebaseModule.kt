package com.dsphoenix.partygatherer.di

import com.dsphoenix.partygatherer.data.firebase.FirestoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesFirestoreService(): FirestoreService = FirestoreService()
}
