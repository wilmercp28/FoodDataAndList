package com.wilmer.fooddataandlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.fooddataandlist.data.mock.getMockFoodList
import com.wilmer.fooddataandlist.data.model.Food
import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.data.remote.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    private val _foodList = MutableStateFlow<List<FoodList>>(getMockFoodList(100))
    val foodList: MutableStateFlow<List<FoodList>> get() = _foodList

    suspend fun fetchFoodDetails(fdcId: String): FoodDetail? {
            return repository.getFoodDetails(fdcId).body()
    }

   suspend fun fetchFoodSearch(query: String): List<Food?> {
        return repository.searchFoods(query, 0, 50)?.foods ?: emptyList()
    }


}
