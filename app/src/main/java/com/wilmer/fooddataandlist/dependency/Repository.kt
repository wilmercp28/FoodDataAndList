package com.wilmer.fooddataandlist.dependency

import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: FatSecretApiService): Repository {
        return Repository(apiService)
    }
}

