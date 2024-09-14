package com.wilmer.fooddataandlist.data.repository

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.remote.OAuthInterceptor
import com.wilmer.fooddataandlist.data.remote.OAuthTokenService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named


class Repository @Inject constructor(
    private val apiService: FatSecretApiService,
    private val tokenService: OAuthTokenService
) {

    @Volatile
    private var updatedApiService: FatSecretApiService? = apiService


    suspend fun fetchAccessToken(clientId: String, clientSecret: String, scope: String): String {
        val response = tokenService.getAccessToken(
            grantType = "client_credentials",
            clientId = clientId,
            clientSecret = clientSecret,
            scope = scope
        )

        if (response.isSuccessful) {
            val tokenResponse = response.body() ?: throw Exception("Empty token response")
            Log.d("Repository", "Access Token: ${tokenResponse.accessToken}")
            return tokenResponse.accessToken
        } else {
            throw Exception("Failed to obtain access token: ${response.code()}")
        }
    }


    @Synchronized
    fun updateOkHttpClient(
        consumerKey: String,
        consumerSecret: String,
        accessToken: String,
        accessSecret: String
    ) {
        val newClient = OkHttpClient.Builder()
            .addInterceptor(
                OAuthInterceptor(
                    consumerKey,
                    consumerSecret,
                    accessToken,
                    accessSecret
                )
            )
            .build()

        val newRetrofit = Retrofit.Builder()
            .baseUrl("https://api.fatsecret.com")
            .client(newClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        updatedApiService = newRetrofit.create(FatSecretApiService::class.java)
    }

    suspend fun searchFoods(
        searchExpression: String,
        pageNumber: Int,
        maxResults: Int,
        includeSubCategories: Boolean,
        includeFoodImages: Boolean,
        includeFoodAttributes: Boolean,
        flagDefaultServing: Boolean,
        format: String
    ): FoodsSearchResponse {
        val api = updatedApiService ?: apiService

        return try {
            val response = api.searchFoods(
                searchExpression, pageNumber, maxResults, includeSubCategories,
                includeFoodImages, includeFoodAttributes, flagDefaultServing, format
            )

            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw Exception("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
