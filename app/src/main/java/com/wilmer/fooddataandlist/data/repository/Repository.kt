package com.wilmer.fooddataandlist.data.repository

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.remote.OAuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: FatSecretApiService
) {



    suspend fun fetchFoods(apiKey: String) {
        val response = apiService.searchFoods(
            searchExpression = "apple",
            pageNumber = 1,
            maxResults = 10,
            includeSubCategories = true,
            includeFoodImages = false,
            includeFoodAttributes = true,
            flagDefaultServing = "true",
            region = "US",
            language = "en",
            apiKey = apiKey
        )

        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                println("Raw Response: ${responseBody.string()}")
            }
        } else {
            println("Error: ${response.errorBody()?.string()}")
        }
    }


}