package com.wilmer.fooddataandlist.viewmodel

import androidx.lifecycle.ViewModel
import com.wilmer.fooddataandlist.data.mock.getMockShoppingList
import com.wilmer.fooddataandlist.data.model.FoodsSearchResponse
import com.wilmer.fooddataandlist.data.model.ShoppingList
import com.wilmer.fooddataandlist.data.repository.Repository
import com.wilmer.fooddataandlist.utilities.ListManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
open class FoodViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _shoppingLists = MutableStateFlow<List<ShoppingList>>(getMockShoppingList(100))
    open val shoppingLists: StateFlow<List<ShoppingList>> get() = _shoppingLists

    private val _foodSearchResult = MutableStateFlow<FoodsSearchResponse?>(null)
    val foodSearchResult: StateFlow<FoodsSearchResponse?> get() = _foodSearchResult

    val listManager = ListManager(_shoppingLists)

    fun changeShoppingList(shoppingList: List<ShoppingList>) {
        _shoppingLists.value = shoppingList
    }


    suspend fun searchFoods(
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
        _foodSearchResult.value = null
        delay(1000)
        _foodSearchResult.value = repository.searchFoods(
            searchExpression, pageNumber, maxResults, includeSubCategories,
            includeFoodImages, includeFoodAttributes, flagDefaultServing, format
        )
    }

}
