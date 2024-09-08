package com.wilmer.fooddataandlist.data.model

enum class ApiResponseFormat{
    abridged,
    full
}

enum class NutrietsQuerry(val ids: Array<Int>){
    Short(arrayOf(203,204,205)),
}