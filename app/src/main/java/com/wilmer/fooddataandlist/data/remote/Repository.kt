package com.wilmer.fooddataandlist.data.remote

import android.util.Log
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.cache.Cache

import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import com.wilmer.fooddataandlist.data.model.NutrietsQuerry
import retrofit2.Response


class FoodRepository(private val apiService: ApiService) {

    companion object {
        private const val APIKEY = BuildConfig.API_KEY
    }

    private val foodDetailsCache = Cache<Int, FoodDetail>()
    private val searchCache = Cache<String, FoodSearchResponse>()

    suspend fun getFoodDetails(fdcId: Int, nutrients: NutrietsQuerry, format: String): FoodDetail? {
        return foodDetailsCache.get(fdcId) ?: try {
            val response = apiService.getFoodDetails(fdcId,format,nutrients.ids,APIKEY)
            Log.d("FoodRepository", "Response: $response")
            if (response.isSuccessful) {
                foodDetailsCache.put(fdcId, response.body()!!)
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    }


    suspend fun getPagedFoodList(page: Int, size: Int): Response<String> {
        return apiService.getPagedFoodList(page, size, APIKEY)
    }


    suspend fun searchFoods(query: String, page: Int, size: Int): FoodSearchResponse? {
        return searchCache.get(query) ?: try {
            val response = apiService.searchFoods(
                query = query,
                pageNumber = page,
                pageSize = size,
                apiKey = APIKEY
            )
            if (response.isSuccessful) {
                searchCache.put(query, response.body()!!)
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}

