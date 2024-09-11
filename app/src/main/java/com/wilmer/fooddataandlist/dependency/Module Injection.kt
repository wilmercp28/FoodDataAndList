package com.wilmer.fooddataandlist.dependency

import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.remote.OAuthService
import com.wilmer.fooddataandlist.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFatSecretRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://platform.fatsecret.com/rest/") // Base URL for FatSecret API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideFatSecretApiService(retrofit: Retrofit): FatSecretApiService {
        return retrofit.create(FatSecretApiService::class.java)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiService: FatSecretApiService
    ): Repository {
        return Repository(apiService)
    }
}
