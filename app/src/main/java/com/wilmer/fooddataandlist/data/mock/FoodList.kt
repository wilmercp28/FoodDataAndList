package com.wilmer.fooddataandlist.data.mock

import com.wilmer.fooddataandlist.data.model.FoodList


val listOfItems = listOf(
    2345004,
    2345046,
    2345048,
    2344876,
    2343120,
    167943,
    2261422,
    2344206,
    2345054,
    168446,
    170092,
    2345005,
    2345007,
    2345228,
    2344879,
    2344915,
    2344916,
    2344932,
    2344939,
    2344938,
    2344986,
    2344942,
    2345049,
    2344877,
    2344896,
    173508,
    2341898,
    2343121,
    2343128,
    2343494,
    2345053,
    173343,
    2344985,
    2344983,
    170032,
    168854,
    2345223,
    2345868,
    2345217,
    2345210,
    168853,
    2344927,
    2344926,
    2343697,
    2345230,
    2345221,
    2341957,
    2344929,
    2344928,
    2344940
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

