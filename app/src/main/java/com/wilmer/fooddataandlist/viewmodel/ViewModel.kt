package com.wilmer.fooddataandlist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.fooddataandlist.BuildConfig
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.Resource
import com.wilmer.fooddataandlist.data.remote.FatSecretApiService
import com.wilmer.fooddataandlist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: Repository,
    private val apiService: FatSecretApiService
) : ViewModel() {

    private val _foodSearchResult = MutableStateFlow<FoodsSearchResponse?>(null)
    val foodSearchResult: StateFlow<FoodsSearchResponse?> get() = _foodSearchResult

     fun searchFoods(
        searchExpression: String,
        pageNumber: Int = 0,
        maxResults: Int = 50,
        includeSubCategories: Boolean = true,
        includeFoodImages: Boolean = true,
        includeFoodAttributes: Boolean = true,
        flagDefaultServing: Boolean = false,
        format: String = "json"
    ) {
        if (searchExpression.isBlank()) return
        viewModelScope.launch {
            _foodSearchResult.value = null
            delay(1000)
            _foodSearchResult.value = repository.searchFoods(
                searchExpression, pageNumber, maxResults, includeSubCategories,
                includeFoodImages, includeFoodAttributes, flagDefaultServing, format
            )
            Log.d("FoodViewModel", "Search result: ${_foodSearchResult.value}")
        }
    }

}
