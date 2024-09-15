package com.wilmer.fooddataandlist.data.repository

import android.util.Log
import com.wilmer.fooddataandlist.data.cache.Cache
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.data.model.Suggestions
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import java.time.Duration
import javax.inject.Inject
import javax.inject.Named


class Repository @Inject constructor(
    private val apiService: FatSecretApiService,
) {

    private val cache =
        Cache<String, Any>(maxSize = 100, expireAfterMillis = Duration.ofHours(24).toMillis())


    suspend fun searchFoods(
        searchExpression: String,
        pageNumber: Int,
        maxResults: Int,
        includeSubCategories: Boolean,
        includeFoodImages: Boolean,
        includeFoodAttributes: Boolean,
        flagDefaultServing: Boolean,
        format: String = "json"
    ): FoodsSearchResponse {

        val cacheKey =
            "$searchExpression-$pageNumber-$maxResults-$includeSubCategories-$includeFoodImages-$includeFoodAttributes-$flagDefaultServing-$format"

        // Try to get the result from the cache
        val cachedResult = cache.get(cacheKey)
        if (cachedResult != null) {
            Log.d("Repository", "Returning cached result for key: $cacheKey")
            return cachedResult as FoodsSearchResponse
        }

        return try {
            val response = apiService.searchFoods(
                searchExpression, pageNumber, maxResults, includeSubCategories,
                includeFoodImages, includeFoodAttributes, flagDefaultServing, format
            )

            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                cache.put(cacheKey, result)
                result
            } else {
                throw Exception("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun autoCompleteSearch(
        searchExpression: String,
        pageNumber: Int,
        maxResults: Int,
        includeSubCategories: Boolean,
        includeFoodImages: Boolean,
        includeFoodAttributes: Boolean,
        flagDefaultServing: Boolean,
        format: String = "json"
    ): Suggestions {

        val cacheKey =
            "$searchExpression-$pageNumber-$maxResults-$includeSubCategories-$includeFoodImages-$includeFoodAttributes-$flagDefaultServing-$format"

        val cachedResult = cache.get(cacheKey)
        if (cachedResult != null) {
            Log.d("Repository", "Returning cached result for key: $cacheKey")
            return cachedResult as Suggestions
        }

        return try {
            val response = apiService.autocomplete(
                searchExpression,maxResults, format
            )

            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!
                cache.put(cacheKey, result)
                result
            } else {
                throw Exception("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

}
