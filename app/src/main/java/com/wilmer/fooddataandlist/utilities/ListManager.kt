package com.wilmer.fooddataandlist.utilities

import com.wilmer.fooddataandlist.data.model.ShoppingList
import kotlinx.coroutines.flow.MutableStateFlow

class ListManager(private val shoppingList: MutableStateFlow<List<ShoppingList>>) {


    fun addShoppingList(newShoppingList: ShoppingList) {
        shoppingList.value += newShoppingList
    }
    fun removeShoppingList(shoppingList: ShoppingList) {
        this.shoppingList.value -= shoppingList
    }
    fun updateShoppingList(shoppingList: ShoppingList) {
        val newList = this.shoppingList.value.toMutableList()
        newList[newList.indexOf(shoppingList)] = shoppingList
        this.shoppingList.value = newList
    }


}
