package com.wilmer.fooddataandlist.data.mock

import com.wilmer.fooddataandlist.data.model.FoodList
import com.wilmer.fooddataandlist.data.model.Item


val listOfItems = listOf(
    Item(2345004),
    Item(2345046),
    Item(2345048),
    Item(2344876),
    Item(2343120),
    Item(167943),
    Item(2261422),
    Item(2344206),
    Item(2345054),
    Item(168446),
    Item(170092),
    Item(2345005),
    Item(2345007),
    Item(2345228),
    Item(2344879),
    Item(2344915),
    Item(2344916),
    Item(2344932),
    Item(2344939),
    Item(2344938),
    Item(2344986),
    Item(2344942),
    Item(2345049),
    Item(2344877),
    Item(2344896),
    Item(173508),
    Item(2341898),
    Item(2343121),
    Item(2343128),
    Item(2343494),
    Item(2345053),
    Item(173343),
    Item(2344985),
    Item(2344983),
    Item(170032),
    Item(168854),
    Item(2345223),
    Item(2345868),
    Item(2345217),
    Item(2345210),
    Item(168853),
    Item(2344927),
    Item(2344926),
    Item(2343697),
    Item(2345230),
    Item(2345221),
    Item(2341957),
    Item(2344929),
    Item(2344928),
    Item(2344940)
)

fun getMockFoodList(
    numberToGenerate: Int = 10,
): MutableList<FoodList> {
    val foodList = mutableListOf<FoodList>()
    for (i in 1..numberToGenerate) {
        val list = FoodList(
            name = "List $i",
            items = listOfItems
        )
        foodList.add(list)
    }
    return foodList
}

