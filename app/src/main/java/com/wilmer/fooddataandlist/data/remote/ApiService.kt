package com.wilmer.fooddataandlist.data.remote


import com.wilmer.fooddataandlist.data.model.Food
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Suggestions
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Query

interface FatSecretApiService {
    @GET("foods/search/v3")
    suspend fun searchFoods(
        @Query("search_expression") searchExpression: String? = null,
        @Query("page_number") pageNumber: Int = 0,
        @Query("max_results") maxResults: Int = 50,
        @Query("include_sub_categories") includeSubCategories: Boolean = false,
        @Query("include_food_images") includeFoodImages: Boolean = false,
        @Query("include_food_attributes") includeFoodAttributes: Boolean = false,
        @Query("flag_default_serving") flagDefaultServing: Boolean = false,
        @Query("format") format: String = "json"
    ): Response<FoodsSearchResponse>


    @GET("food/v4")
    suspend fun getFoodDetails(
        @Query("food_id") foodId: String,
        @Query("include_sub_categories") includeSubCategories: Boolean = false,
        @Query("include_food_images") includeFoodImages: Boolean = false,
        @Query("include_food_attributes") includeFoodAttributes: Boolean = false,
        @Query("flag_default_serving") flagDefaultServing: Boolean = false,
        @Query("format") format: String = "json"
    ): Response<Food>


    @GET("food/autocomplete/v2")
    suspend fun autocomplete(
        @Query("search_expression") searchExpression: String,
        @Query("max_results") maxResults: Int = 50,
        @Query("format") format: String = "json"
    ): Response<Suggestions>

    @GET("food/barcode/find-by-id/v1")
    suspend fun findFoodByBarcode(
        @Query("barcode") barcode: String, //13-digit GTIN-13 formatted sequence of digits representing the barcode to search against
        @Query("format") format: String = "json"
    ): Response<Food>


}






