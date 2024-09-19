package com.wilmer.fooddataandlist.data.mock

import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.repository.Repository
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getMockViewModel(

): FoodViewModel {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://platform.fatsecret.com/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(FatSecretApiService::class.java)

    val repository = Repository(apiService)

    val viewModel = FoodViewModel(repository)
    viewModel.changeShoppingList(getMockShoppingList(20))

    return viewModel


}