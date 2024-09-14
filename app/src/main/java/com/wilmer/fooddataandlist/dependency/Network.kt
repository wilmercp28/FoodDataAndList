package com.wilmer.fooddataandlist.dependency

import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.OAuthTokenResponse
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.remote.OAuthInterceptor
import com.wilmer.fooddataandlist.data.remote.OAuthTokenService
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("consumerKey")
    fun provideConsumerKey(): String {
        return BuildConfig.CONSUMER_KEY // Static consumer key
    }

    @Provides
    @Singleton
    @Named("consumerSecret")
    fun provideConsumerSecret(): String {
        return BuildConfig.CONSUMER_SECRET // Static consumer secret
    }

    @Provides
    @Singleton
    @Named("clientId")
    fun provideClientId(): String {
        return BuildConfig.CLIENT_ID
    }

    @Provides
    @Singleton
    @Named("clientSecret")
    fun provideClientSecret(): String {
        return BuildConfig.CLIENT_SECRET
    }

    @Provides
    @Singleton
    @Named("apiOkHttpClient")
    fun provideOkHttpClientForApi(
        @Named("consumerKey") consumerKey: String,
        @Named("consumerSecret") consumerSecret: String
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor(consumerKey, consumerSecret, null, null))
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("tokenOkHttpClient")
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Provide Retrofit for API calls
    @Provides
    @Singleton
    @Named("apiRetrofit")
    fun provideRetrofitForApi(@Named("apiOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://platform.fatsecret.com/rest/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide Retrofit for access token calls
    @Provides
    @Singleton
    @Named("tokenRetrofit")
    fun provideRetrofitForToken(@Named("tokenOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://oauth.fatsecret.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide the FatSecret API service
    @Provides
    @Singleton
    fun provideFatSecretApiService(@Named("apiRetrofit") retrofit: Retrofit): FatSecretApiService {
        return retrofit.create(FatSecretApiService::class.java)
    }

    // Provide the OAuth token service
    @Provides
    @Singleton
    fun provideOAuthTokenService(@Named("tokenRetrofit") retrofit: Retrofit): OAuthTokenService {
        return retrofit.create(OAuthTokenService::class.java)
    }
}

