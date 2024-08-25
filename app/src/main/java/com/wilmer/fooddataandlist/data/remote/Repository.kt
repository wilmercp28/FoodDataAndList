package com.wilmer.fooddataandlist.data.remote

import android.util.Log
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import retrofit2.Response

const val apiKey = BuildConfig.API_KEY

class FoodRepository(private val apiService: ApiService) {

    suspend fun getFoodDetails(fdcId: String): Response<String> {
        return apiService.getFoodDetails(fdcId, apiKey)
    }

    suspend fun getMultipleFoodDetails(fdcIds: List<String>): Response<String> {
        return apiService.getMultipleFoodDetails(fdcIds, apiKey)
    }

    suspend fun getPagedFoodList(page: Int, size: Int): Response<String> {
        return apiService.getPagedFoodList(page, size, apiKey)
    }

    suspend fun searchFoods(query: String, page: Int, size: Int): FoodSearchResponse? {
        val response =apiService.searchFoods(query = query, apiKey =  apiKey)
        Log.e("response",response.toString())
        return response.body()
    }

}
