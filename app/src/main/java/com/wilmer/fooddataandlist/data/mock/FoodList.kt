package com.wilmer.fooddataandlist.data.mock

import com.wilmer.fooddataandlist.data.model.FoodList

fun getMockFoodList(
    numberToGenerate: Int = 10,
): MutableList<FoodList> {
    val foodList = mutableListOf<FoodList>()
    for (i in 1..numberToGenerate) {
        val list = FoodList(
            name = "List $i",
            items = listOf(1,2,3,4,5,6,7,8)
        )
        foodList.add(list)
    }
    return foodList
}