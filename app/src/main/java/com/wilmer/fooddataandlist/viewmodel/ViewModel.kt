package com.wilmer.fooddataandlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.fooddataandlist.data.model.FoodSearchResponse
import com.wilmer.fooddataandlist.data.remote.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository) : ViewModel() {

    private val _foodDetails = MutableStateFlow<String>("")
    val foodDetails: Flow<String> = _foodDetails

    private val _foodSearch = MutableStateFlow<FoodSearchResponse?>(null)
    val foodSearch: StateFlow<FoodSearchResponse?>  get() = _foodSearch

    fun fetchFoodDetails(fdcId: String) {
        viewModelScope.launch {
            _foodDetails.value = repository.getFoodDetails(fdcId).body().toString()
        }
    }

    fun fetchFoodSearch(query: String) {
        viewModelScope.launch {
            _foodSearch.value = repository.searchFoods(query,1,10)
            Log.e("response",_foodSearch.value.toString())

        }
    }
}
