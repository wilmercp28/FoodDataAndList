package com.wilmer.fooddataandlist.dependency

import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.remote.OAuthInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val consumerKey = BuildConfig.CONSUMER_KEY
        val consumerSecret = BuildConfig.CONSUMER_SECRET
        val accessToken = null
        val accessSecret = null

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor(consumerKey, consumerSecret, accessToken, accessSecret))
            .addInterceptor(loggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://platform.fatsecret.com/rest/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFatSecretApiService(retrofit: Retrofit): FatSecretApiService {
        return retrofit.create(FatSecretApiService::class.java)
    }
}
