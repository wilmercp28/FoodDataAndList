package com.wilmer.fooddataandlist.viewmodel

import androidx.lifecycle.ViewModel
import com.wilmer.fooddataandlist.data.mock.getMockFoodList
import com.wilmer.fooddataandlist.data.model.ApiResponseFormat
import com.wilmer.fooddataandlist.data.model.FoodDetail
import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.data.model.Item
import com.wilmer.fooddataandlist.data.model.NutrietsQuerry
import com.wilmer.fooddataandlist.data.remote.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {


    private val _foodDetails = MutableStateFlow<Map<Int, FoodDetail>>(emptyMap())
    val foodDetails: MutableStateFlow<Map<Int, FoodDetail>> get() = _foodDetails

    private val _foodList = MutableStateFlow<List<FoodList>>(getMockFoodList(100))
    val foodList: MutableStateFlow<List<FoodList>> get() = _foodList

    fun checkItem(item: Item, listIndex: Int) {
        _foodList.update { currentList ->
            currentList.toMutableList().apply {
                this[listIndex].items.find { it.id == item.id }?.let { foundItem ->
                    val updatedItem = foundItem.copy(checked = !foundItem.checked)
                    val updatedItems = this[listIndex].items.toMutableList().apply {
                        val itemIndex = indexOf(foundItem)
                        this[itemIndex] = updatedItem
                    }
                    this[listIndex] = this[listIndex].copy(items = updatedItems)
                }
            }
        }
    }

    suspend fun fetchFoodDetails(fdcId: Int) {
        val response = repository.getFoodDetails(fdcId,NutrietsQuerry.Short,ApiResponseFormat.abridged.name)
        if (response != null) {
            _foodDetails.update { currentMap ->
                currentMap.toMutableMap().apply {
                    this[fdcId] = response
                }
            }
        }
    }


    suspend fun fetchFoodSearch(query: String): List<FoodDetail?> {
        return repository.searchFoods(query, 0, 50)?.foods ?: emptyList()
    }


}
