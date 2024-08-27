package com.wilmer.fooddataandlist.data.remote

import android.util.Log
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

const val apiKey = BuildConfig.API_KEY

class FoodRepository(private val apiService: ApiService) {

    suspend fun getFoodDetails(fdcId: String): Response<FoodDetail> {
        return apiService.getFoodDetails(fdcId, apiKey)
    }


    suspend fun getPagedFoodList(page: Int, size: Int): Response<String> {
        return apiService.getPagedFoodList(page, size, apiKey)
    }


    private val cache = mutableMapOf<String, FoodSearchResponse>()

    suspend fun searchFoods(query: String, page: Int, size: Int): FoodSearchResponse? {
        val cacheKey = "$query-$page-$size"
        return cache[cacheKey] ?: try {
            val response = apiService.searchFoods(
                query = query,
                pageNumber = page,
                pageSize = size,
                apiKey = apiKey
            )
            if (response.isSuccessful) {
                cache[cacheKey] = response.body()!!
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}

