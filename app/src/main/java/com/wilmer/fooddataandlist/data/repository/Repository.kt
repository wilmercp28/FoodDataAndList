package com.wilmer.fooddataandlist.data.repository

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: FatSecretApiService
) {
    suspend fun searchFoods(
        searchExpression: String,
        pageNumber: Int,
        maxResults: Int,
        includeSubCategories: Boolean,
        includeFoodImages: Boolean,
        includeFoodAttributes: Boolean,
        flagDefaultServing: Boolean,
        format: String
    ): Resource<FoodsSearchResponse> {
        return try {
            val response = apiService.searchFoods(
                searchExpression, pageNumber, maxResults, includeSubCategories,
                includeFoodImages, includeFoodAttributes, flagDefaultServing, format
            )
            Log.d("Repository", "ResponseRaw: ${response.body()}")
            if (response.isSuccessful && response.body() != null) {
                Log.d("Repository", "Response: ${response.body()}")
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.message}")
        }
    }

}