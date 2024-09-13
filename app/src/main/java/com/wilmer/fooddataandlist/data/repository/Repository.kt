package com.wilmer.fooddataandlist.data.repository

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    ): FoodsSearchResponse {

        return try {
            val response = apiService.searchFoods(
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