package com.wilmer.fooddataandlist.data.mock

import com.wilmer.fooddataandlist.data.model.Item
import com.wilmer.fooddataandlist.data.model.ShoppingList

fun getMockShoppingList(numberOfLists: Int): List<ShoppingList> {
    val shoppingLists = mutableListOf<ShoppingList>()
    for (i in 1..numberOfLists) {
        shoppingLists.add(
            ShoppingList(
                name = "Shopping List $i",
                items = emptyList<Item>()
            )
        )

    }
    return shoppingLists
}