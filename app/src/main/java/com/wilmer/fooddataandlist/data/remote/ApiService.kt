package com.wilmer.fooddataandlist.data.remote


import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Query

interface FatSecretApiService {
    @GET("foods/search/v3")
    suspend fun searchFoods(
        @Query("search_expression") searchExpression: String? = null,
        @Query("page_number") pageNumber: Int = 0,
        @Query("max_results") maxResults: Int = 20,
        @Query("include_sub_categories") includeSubCategories: Boolean = false,
        @Query("include_food_images") includeFoodImages: Boolean = false,
        @Query("include_food_attributes") includeFoodAttributes: Boolean = false,
        @Query("flag_default_serving") flagDefaultServing: Boolean = false,
        @Query("format") format: String = "json"
    ): Response<FoodsSearchResponse>
}






