package com.wilmer.fooddataandlist.data.remote


import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("food/{fdcId}")
    suspend fun getFoodDetails(
        @Path("fdcId") fdcId: Int,
        @Query("format") format: String,
        @Query("nutrients") nutrients: Array<Int>,
        @Query("api_key") apiKey: String,
    ): Response<FoodDetail>

    @Headers("Content-Type: application/json")
    @GET("/v1/foods")
    suspend fun getMultipleFoodDetails(
        @Query("fdcIds") fdcIds: String,
        @Query("api_key") apiKey: String,
    ): Response<String>

    @Headers("Content-Type: application/json")
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

