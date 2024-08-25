package com.wilmer.fooddataandlist.data.remote

import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/food/{fdcId}")
    suspend fun getFoodDetails(
        @Path("fdcId") fdcId: String,
        @Query("api_key") apiKey: String,
    ): Response<String>

    @GET("/v1/foods")
    suspend fun getMultipleFoodDetails(
        @Query("fdcIds") fdcIds: String,  // Pass as a comma-separated string
        @Query("api_key") apiKey: String,
    ): Response<String>

    @GET("/v1/foods/list")
    suspend fun getPagedFoodList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("api_key") apiKey: String,
    ): Response<String>

    @Headers("Content-Type: application/json")
    @GET("foods/search")
   suspend fun searchFoods(
        @Query("query") query: String,
        @Query("pageSize") pageSize: Int = 50,
        @Query("pageNumber") pageNumber: Int = 0,
        @Query("api_key") apiKey: String
    ): Response<FoodSearchResponse>
}

